package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.DiscountCodeEntity;
/**
 *
 * @author marie
 */
public class DiscountCodeDAO {
    protected final DataSource myDataSource;

	/**
	 *
	 * @param dataSource la source de données à utiliser
	 */
	public DiscountCodeDAO(DataSource dataSource) {
		this.myDataSource = dataSource;
	}

	/**
	 *
	 * @return le nombre d'enregistrements dans la table CUSTOMER
	 * @throws DAOException
	 */
	public List<DiscountCodeEntity> showAllCode() throws DAOException {
            List rep = new ArrayList();
            String sql = "SELECT DISCOUNT_CODE, RATE FROM DISCOUNT_CODE";

            try (   Connection connection = myDataSource.getConnection(); 
                    Statement stmt = connection.createStatement(); 
                    ResultSet rs = stmt.executeQuery(sql) 
            ) {
                    while (rs.next()) { 
                        DiscountCodeEntity dc = new DiscountCodeEntity(rs.getString("DISCOUNT_CODE"), rs.getFloat("RATE"));
                        rep.add(dc);
                    }
            } catch (SQLException ex) {
                    Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                    throw new DAOException(ex.getMessage());
            }

            return rep;
        }
        
        /**
	 * Ajouter un enregistrement dans la table DISCOUNT_CODE
	 * @param key le code du discount code, taux le taux de réduction
	 * @return le nombre d'enregistrements détruits (1 ou 0 si pas trouvé)
	 * @throws DAOException
	 */
        public int addCode(String key, Float taux) throws DAOException{
            
            String sql = "INSERT INTO DISCOUNT_CODE (DISCOUNT_CODE, RATE ) VALUES (?,?)  ";
            
            try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)
            ) {
                stmt.setString(1,key);
                stmt.setFloat(2, taux);
			
		return stmt.executeUpdate();

            }  
            catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                throw new DAOException(ex.getMessage());
            }
            
        }
        
        /**
	 * Detruire un enregistrement dans la table DISCOUNT_CODE
	 * @param code le code du discount code
	 * @return le nombre d'enregistrements détruits (1 ou 0 si pas trouvé)
	 * @throws DAOException
	 */
	public int deleteCode(String code) throws DAOException {

		// Une requête SQL paramétrée
		String sql = "DELETE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
		try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)
                ) {
                        // Définir la valeur du paramètre
			stmt.setString(1, code);
			
			return stmt.executeUpdate();

		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
	}
        
        
}
