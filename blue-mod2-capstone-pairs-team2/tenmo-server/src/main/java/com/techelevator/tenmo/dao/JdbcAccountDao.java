package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.RowSet;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal getBalance(int accountId) {
        String sql = "SELECT balance FROM accounts WHERE account_id = ?";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, accountId);
    }

    @Override
    public boolean sendTransfer(int senderId, int receiverId, BigDecimal amount){
        BigDecimal balance = getBalance(senderId);
        if (balance.compareTo(amount) >= 0){
            BigDecimal newBalance = balance.subtract(amount);
            String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
            jdbcTemplate.update(sql, newBalance, senderId);

            BigDecimal receiverNewBalance = getBalance(receiverId).add(amount);
            jdbcTemplate.update(sql, receiverNewBalance, receiverId);
            return true;
        }
        return false;
    }

    @Override
    public List<Transfer> listTransfers(int accountId)
    {
        List<Transfer> transferList = new ArrayList<>();

        String sql = "SELECT * FROM transfers WHERE account_from = ? OR account_to = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, accountId, accountId);
        while(rowSet.next()){
            transferList.add(getTransferDetails(rowSet.getInt("transfer_id")));
        }
        return transferList;
    }

    @Override
    public Transfer getTransferDetails(int transferId) {
        String sql = "SELECT * FROM transfers WHERE transfer_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, transferId);
        Transfer result = null;
        try {
            while (rowSet.next()) {
                int transferTypeId = rowSet.getInt("transfer_type_id");
                int transferStatusId = rowSet.getInt("transfer_status_id");
                int accountFrom = rowSet.getInt("account_from");
                int accountTo = rowSet.getInt("account_to");
                BigDecimal amount = rowSet.getBigDecimal("amount");
                result = new Transfer(transferId, transferTypeId, transferStatusId, accountFrom, accountTo, amount);
            }
        } catch(Exception ex){
            System.out.println("Transfer not found.");
        }
        if (result != null) {
            return result;
        }
        else return null;
    }
}
