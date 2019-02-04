package com.labralab.mkbpro10.implementation.store

import com.labralab.mkbpro10.model.entity.Account
import com.labralab.mkbpro10.model.store.AccountStore
import com.labralab.mkbpro10.model.store.PreferenceManager

import javax.inject.Inject


class DiskAccountStoreImpl
@Inject constructor(private val preferenceManager: PreferenceManager) : AccountStore {

    override fun storeAccount(account: Account) {

    }

    override fun getAccount(): Account? {
        return null
    }

    override fun clearAccount() {

    }

    companion object {

        private val USER_LOGIN = "user_login"
        private val USER_PASSWORD = "user_password"
    }

    //    @Override
    //    public void storeAccount(final Account account) {
    //        Log.d(TAG, "storeAccount: " + account);
    //        preferenceManager.set(ACCOUNT_TOKEN, account.getAuthToken());
    //        preferenceManager.set(USER_NAME, account.getUserName());
    //        preferenceManager.set(COMPANY_KEY, account.getCurrentCompany());
    //        preferenceManager.set(USER_ROLE, userRoleMapper.from(account.getUserRole()));
    //    }
    //
    //    @Nullable
    //    @Override
    //    public Account getAccount() {
    //        String token = preferenceManager.getString(ACCOUNT_TOKEN);
    //        String userName = preferenceManager.getString(USER_NAME);
    //        UserRole role = userRoleMapper.map(preferenceManager.getInt(USER_ROLE));
    //
    //        if (token == null) {
    //            Log.d(TAG, "getAccount: null");
    //            return null;
    //        }
    //
    //        Account account = Account.newBuilder()
    //                .authToken(token)
    //                .currentCompany(getCompany())
    //                .userName(userName)
    //                .userRole(role)
    //                .build();
    //
    //        Log.d(TAG, "getAccount: " + account);
    //
    //        return account;
    //    }
    //
    //    @Nullable
    //    private Company getCompany() {
    //        Company currentCompany = null;
    //        try {
    //            currentCompany = (Company) preferenceManager.getSerializable(COMPANY_KEY);
    //        } catch (Exception e) {
    //            Log.e(TAG, "getCompany: ", e);
    //        }
    //        return currentCompany;
    //    }
    //
    //    @Override
    //    public void clearAccount() {
    //        Log.d(TAG, "clearAccount: ");
    //        preferenceManager.delete(ACCOUNT_TOKEN);
    //        preferenceManager.delete(USER_NAME);
    //        preferenceManager.delete(COMPANY_KEY);
    //        preferenceManager.delete(USER_ROLE);
    //    }
}
