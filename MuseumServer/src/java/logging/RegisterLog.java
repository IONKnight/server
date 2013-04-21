/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logging;

import persistance.DatabaseQueryExecutor;
import utility.DateUtil;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class RegisterLog implements Log {

    DatabaseQueryExecutor db;

    public RegisterLog(DatabaseQueryExecutor db) {
        this.db = db;
    }

    @Override
    public void log(String[] params) {
        String username = params[0];
        String level = params[1];
        String loginCode = params[2];
        String SQL = "INSERT INTO RegisterLog (usernameOfUser, levelOfUser, loginCode, logDate,logTime) VALUES ('" + username + "','" + level + "', '" + loginCode + "','" + DateUtil.getCurrentDate() + "','" + DateUtil.getCurrentTime() + "');";
        db.executeUpdate(SQL);
    }
}