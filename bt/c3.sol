//SPDX-License-Identifier:GPL-3.0

pragma solidity >= 0.5.0 < 0.9.0;

struct Student{

    uint roll ;
    string name ;

}

contract StudentRecord{

    Student[] public school;
    
    
    event log(string _fun,address sender,uint val,bytes data);
    event log2(string _fun,address sender,uint val,string data);
    event log3(string _fun,address sender,uint val,uint data);


    constructor() payable{
        emit log("CONTRACT CREATED", msg.sender,msg.value,msg.data);
    }
    fallback() external payable{
        
        emit log3("GET COUNT FUNCTION CALLED FROM FALLBACK",msg.sender,msg.value,getCount());

       
    }
    receive() external payable{
        emit log("RECEIVE called",msg.sender,msg.value,"REC CALLED");
        
    }

    function addStudent (uint _roll,string memory _name) public payable{

        Student memory s1;
        s1.roll = _roll;
        s1.name = _name;
        school.push(s1);
        

    }

    function getCount () public view returns(uint){
        
        return school.length;

    }

    function removeStudent(uint _roll) public payable returns(string memory ){

        int index = -1;

        string memory res;
        for(uint i = 0;i< school.length;i++){

            if(school[i].roll == _roll){
                index = int(i);
                break;

            }


        }
        require(index != -1 , "No Such Student Exist");

        for(uint i = uint(index);i<school.length - 1;i++){

            school[i] = school[i+1];


        }
        school.pop();
        res = "Student Removed !!";

        return res;

    }


}