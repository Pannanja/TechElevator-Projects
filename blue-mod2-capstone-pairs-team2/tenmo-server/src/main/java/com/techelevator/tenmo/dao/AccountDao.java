package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    BigDecimal getBalance(int accountId);

    boolean sendTransfer(int senderId, int receiverId, BigDecimal amount);

    List<Transfer> listTransfers(int accountId);

    Transfer getTransferDetails(int transferId);
}
