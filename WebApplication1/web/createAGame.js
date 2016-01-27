/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
    function validateForm() {
    var x = document.forms["create"]["description"].value;
    var y=document.forms["create"]["number"].value;
    if (x == null || x == ""||y == null ||y == "") {
        alert("Please Fill All Field");
        return false;
    }
}

    

