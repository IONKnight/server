/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.util.ArrayList;
import visitorsAndGroups.Group;
import visitorsAndGroups.Visitor;

/**
 *
 * @author neil
 */
public class PersistanceRepositoryGroups {
    
    private DatabaseQueryExecutor executor;
    
    public void PeristanceRepositoryGroups(DatabaseQueryExecutor db){
        this.executor = executor;
    }

    public boolean addNewGroupToDatabase(int tourId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Group getLastCreatedGroup() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean addNewVisitorToDatabase(Visitor visitor) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public ArrayList<Group> getAllGroups() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}