package rsvier.workshop.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import org.springframework.stereotype.Component;

import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;

@Component
public class AccountDAOImp implements AccountDAO {

	private Logger logger = LogConnection.getLogger();

	
	@Override
	public List<Account> getAllAccounts() {

		List<Account> accountList = new ArrayList<>();
		

		String query = "SELECT * FROM account";

		try (Connection conn = DataSource.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {

			while (resultSet.next()) {
				Account account = new Account();
				account.setAccountId(resultSet.getInt(1));
				account.setAccountType(resultSet.getInt(2));
				account.setEmail(resultSet.getString(3));
				account.setPassword(resultSet.getString(4));
				accountList.add(account);

			}
			logger.log(Level.WARNING, "Account list successfully returned");
			return accountList;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occurred", e);
		}

		
		return null;
	}


	@Override
	public int createAccount(Account account) {

		int generatedId = 0;

		String query = "INSERT INTO account (account_type,email,password) VALUES(?,?,?)";

		try (Connection conn = DataSource.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query,
						PreparedStatement.RETURN_GENERATED_KEYS);) {
			
			preparedStatement.setInt(1, account.getAccountType());
			preparedStatement.setString(2, account.getEmail());
			preparedStatement.setString(3, account.getPassword());

			preparedStatement.executeUpdate();

			try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
				if (rs.next()) {
					generatedId = rs.getInt(1);
					logger.log(Level.INFO, "Account successfully created");
					
				}
			}
			System.out.println("Account is succesvol aangemaakt");
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occurred ", e);
		}

		return generatedId;

	}

	
	public void updateAccount(Account account) {

		String query = "UPDATE account SET account_type = ?, email = ?, password = ? WHERE account_id = ?";

		try (Connection conn = DataSource.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {
			
			preparedStatement.setInt(1, account.getAccountType());
			preparedStatement.setString(2, account.getEmail());
			preparedStatement.setString(3, account.getPassword());
			preparedStatement.setInt(4, account.getAccountId());

			preparedStatement.executeUpdate();
			
			logger.log(Level.INFO, "Account successfully updated");
			System.out.println("Account successfully updated ");

		} catch (SQLException e) {

			logger.log(Level.WARNING, "SQL exception occurred ", e);

		}

	}

	@Override
	public void deleteAccount(Account account) {

		String query = "DELETE FROM account WHERE account_id = ?";

		try (Connection conn = DataSource.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setInt(1, account.getAccountId());

			preparedStatement.executeUpdate();
			
			logger.log(Level.INFO, "Account succesfully deleted");
			System.out.println("Account successfully deleted ");

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occurred ", e);
		}

	}

	@Override
	public Account getAccountByEmail(String email) {

		Account account = new Account();

		String query = "SELECT * FROM account WHERE email = ?";

		try (Connection conn = DataSource.getConnection();
			 PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setString(1, email);

			try (ResultSet resultSet = preparedStatement.executeQuery();) {

				if (resultSet.next()) {
					
					account.setAccountId(resultSet.getInt(1));
					account.setAccountType(resultSet.getInt(2));
					account.setEmail(resultSet.getString(3));
					account.setPassword(resultSet.getString(4));
				}

			}
			logger.log(Level.WARNING, "Account successfully returned");
			return account;

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occurred ", e);
		}

		return null;
	}

	
	@Override
	public Account getAccountById(int accountId) {
		Account account = new Account();

		String query = "SELECT * FROM account WHERE account_id = ?";

		try (Connection conn = DataSource.getConnection();
			 PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setInt(1, accountId);

			try (ResultSet resultSet = preparedStatement.executeQuery();) {

				if (resultSet.next()) {

					account.setAccountId(resultSet.getInt(1));
					account.setAccountType(resultSet.getInt(2));
					account.setEmail(resultSet.getString(3));
					account.setPassword(resultSet.getString(4));
				}

			}
			logger.log(Level.WARNING, "Account successfully returned");
			return account;

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occurred ", e);
		}

		return null;
	}

}