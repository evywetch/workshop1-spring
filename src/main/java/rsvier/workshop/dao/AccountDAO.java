
package rsvier.workshop.dao;

import java.util.List;

import rsvier.workshop.domain.*;

public interface AccountDAO {

	public List<Account> getAllAccounts();

	public int createAccount(Account account);

	public void updateAccount(Account account);

	public void deleteAccount(Account account);

	public Account getAccountByEmail(String email);
	
	public Account getAccountById(int accountId);


}
