import httpClient from '../http-common';

const createCreditApplicationByRut = (rut, loanType) => {
    console.log("Rut:", rut, "Loan Type:", loanType);
    return httpClient.post(`credit-application/application/create`, { 
        rut: rut, 
        loan_type: loanType 
    });
}



export default { createCreditApplicationByRut };