# FinSafe Transaction Validator

## Overview
A Core Java console application that simulates a digital wallet. It validates transactions, prevents overdrafts, and maintains a mini transaction history.

## Features
- Deposit and withdraw money
- Prevents invalid and overdraft transactions
- Custom exception for insufficient funds
- Stores last 5 transactions
- Displays mini statement

## Concepts Used
- Encapsulation (Account class)
- Custom Exception (InSufficientFundsException)
- ArrayList for transaction history
- Exception handling

## Validation Rules
- Deposit/withdraw amount must be positive
- Withdrawal exceeding balance is not allowed
