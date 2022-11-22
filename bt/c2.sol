//SPDX-License-Identifier:MIT

pragma solidity >= 0.5.0 < 0.9.0;

struct Customer{

    address account_no;
    uint balance;

}


contract Bank{

    uint public bankSecurity ;
    mapping(address => Customer) bankCustomers;
    mapping(address => bool) customerExists;
    event log(bool status,string msg);

    constructor() payable{
        bankSecurity = msg.value;
         
    }


    modifier accountExist(address _addr) {
        require (customerExists[_addr] == true, " Customer Does Not Exist ");
        _;
    }
    modifier enoughBalance(address _addr, uint _amt){
        require(bankCustomers[_addr].balance >= _amt*1e18,"Not Enough Balance Available in the account");
        _;
    }

    function openAccount() public payable{

        Customer memory c1;
        c1.account_no = msg.sender;
        c1.balance = msg.value;
        bankSecurity += msg.value;
        bankCustomers[msg.sender] = c1;
        customerExists[msg.sender] = true;


    }

    function depositMoney(address _addr) public  payable accountExist(_addr)  returns (bool status, string memory message){

        bankSecurity += msg.value;
        bankCustomers[_addr].balance += msg.value;
        emit log(true ,"Amount Deposited");
        return (true,"Amount Deposited ");


    }

    function withdrawMoney(uint _amt) public accountExist(msg.sender) enoughBalance(msg.sender,_amt) returns(bool status,string memory message){

        _amt *= 1e18;
        bankSecurity -= _amt;
        payable(msg.sender).transfer(_amt);
        bankCustomers[msg.sender].balance -= _amt;
        emit log(true ,"Amount Withdrawn");
        return (true,"Amount Withdrawn successfully");

    }

    function viewBalance(address _addr) public view accountExist(_addr) returns (bool status,uint amount){

        return (true,bankCustomers[_addr].balance);


    }
    



}