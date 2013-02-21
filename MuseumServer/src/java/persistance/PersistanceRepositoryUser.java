package persistance;

import domainObjects.User;
import domainObjects.UserTypes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class PersistanceRepositoryUser {

    private DatabaseQueryExecutor db;

    public PersistanceRepositoryUser(DatabaseQueryExecutor db) {
        this.db = db;

    }

    public ArrayList<User> getAllUsers() {

        String sql = "SELECT * FROM "
                + "`user` `u` , `usertype` `ut` , `usertypelink` `utl` "
                + "WHERE `u`.`userID` = `utl`.`userID` "
                + "AND `ut`.`typeID` = `utl`.`typeID`";

        ResultSet rs = db.executeStatement(sql);
        return mapResultSetToArrayList(rs);
    }

    private ArrayList<User> mapResultSetToArrayList(ResultSet rs) {

        ArrayList<User> userList = new ArrayList();
        String username = "";
        String password = "";
        int userID = 0;
        UserTypes.UserType type;
        User newUser = null;

        try {
            while (rs.next()) {
                if (rs.getString("userName").equalsIgnoreCase(username)) {
                    addUserTypeToUser(rs, newUser);
                }else{
                    userID = Integer.parseInt(rs.getString("userID"));
                    username = rs.getString("userName");
                    password = rs.getString("password");                    
                    newUser = new User(username, password, userID);
                    addUserTypeToUser(rs, newUser);
                    userList.add(newUser);
                }
                addAccessibleDirsToUser(rs, newUser);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistanceRepositoryUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userList;
    }

    private void addUserTypeToUser(ResultSet rs, User newUser) throws SQLException {

        UserTypes.UserType type;
        type = UserTypes.UserType.valueOf(rs.getString("typeName"));
        newUser.addUserType(type);
    }

    private void addAccessibleDirsToUser(ResultSet rs, User newUser) throws SQLException {
        String directories = rs.getString("accessibleFunctions");
        //if there's only one directory in the accessibleFunctions column then 
        //just add it and return
        if (!directories.contains(",")) {
            newUser.addAccessibleDirectory(directories.trim());
            return;
        }

        //loop over the comma seperated directories and add them to the user
        for (String directory : directories.split(",")) {
            directory = directory.trim();
            newUser.addAccessibleDirectory(directory);
        }
    }
}
