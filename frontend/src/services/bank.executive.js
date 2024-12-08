import httpClient from '../http-common';

//METODOS PARA LA SOLICITUD DE CREDITO
const getMonthlyLoanOfClientByRutForApplication = (rut) => {
    return httpClient.get(`executives/application/client/${rut}/monthly-loan`);
}

const getExpectedAmountOfClientByRutForApplication = (rut) => {
    return httpClient.get(`executives/application/client/${rut}/expected-amount`);
}

const getInteresRateOfClientByRutForApplication = (rut) => {
    return httpClient.get(`executives/application/client/${rut}/interest-rate`);
}

const getTimeLimitOfClientByRutForApplication = (rut) => {
    return httpClient.get(`executives/application/client/${rut}/time-limit`);
}



//METODOS PARA OBTENER ESOS DATOS A PARTIR DE LA EVALUACION DEL CREDITO
const getExpectedAmountOfClientByRutForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}/amount`);
}

const getInteresRateOfClientByRutForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}/interest`);
}

const getTimeLimitOfClientByRutForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}/time`);
}

const getMonthlyLoanOfClientByRutForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}/monthly-loan`);
}

const getClientByRutForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}`);
}

const getDebtsByRutForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}/debts`);
}

const getEmploymentHistoryByRutForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}/employment-history`);
}

const getBankAccountByRutForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}/client-bank-account`);
}

const getDepositInBankAccountByRutForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}/deposit`);
}

const getWithdrawalInBankAccountByRutForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}/withdrawal`);
}



const getFeeIncomeRatioByRutForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}/fee-income`);
}

const getPendingDebtsByRutForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}/pending-debts`);
}

const getPendingDebtsMonthlySalaryByRutForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}/pending-debts-salary-ratio`);
}

const verifyClientAgeForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}/verify-age`);
}


const validateBankAccountConsistencyByRutForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}/account-consistency`);
}

const checkPeriodicDepositsForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/clients/${rut}/periodic-deposits`);
}

const checkLargeWithdrawalsForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/check-large-withdrawals/${rut}`);
}


const getLoanTypeByRutForEvaluation = (rut) => {
    return httpClient.get(`executives/evaluate/${rut}/loanType`);
}






//METODOS PARA OBTENER LOS MISMOS DATOS PARA LA SIMULACIUON DE CREDITO


const getExpectedAmountOfClientByRutForSimulation = (rut) => {
    return httpClient.get(`executives/simulation/client/${rut}/expected-amount`);
}

const getInteresRateOfClientByRutForSimulation = (rut) => {
    return httpClient.get(`executives/simulation/client/${rut}/interest-rate`);
}

const getTimeLimitOfClientByRutForSimulation = (rut) => {
    return httpClient.get(`executives/simulation/client/${rut}/time-limit`);
}

const getMonthlyLoanOfClientByRutForSimulation = (rut) => {
    return httpClient.get(`executives/simulation/client/${rut}/monthly-loan`);
}

const getClientByRutForSimulation = (rut) => {
    return httpClient.get(`executives/simulation/client/${rut}`);
}

/*---------------------------------------------------------*/ 


//METODO PARA SEGUIMIENTO  DE LA SOLICITUD DE CREDITO
const updateCreditApplicationStatusForFollowRequest = (id, newStatus) => {
    return httpClient.put(`executives/follow-requests/update-status/${id}?status=${newStatus}`);
}



const getCreditApplicationsByRutForFollowRequest = (rut) => {
    return httpClient.get(`executives/follow-requests/${rut}/credit-application`);
}

const getCreditApplicationByIdForFollowRequest = (id) => {
    return httpClient.get(`executives/follow-requests/credit-application/${id}`);
}


//METODOS PARA CALCULAR LOS COSTOS TOTALES

const insuranceCalculationByRutForTotalCost = (rut) => {
    return httpClient.get(`executives/total-cost/insurance/${rut}`);
}

const administrationCommissionByRutForTotalCost = (rut) => {
    return httpClient.get(`executives/total-cost/administration-commission/${rut}`);
}

const monthlyCostByRutForTotalCost = (rut) => {
    return httpClient.get(`executives/total-cost/monthly-cost/${rut}`);
}

const totalCostOfLoanByRutForTotalCost = (rut) => {
    return httpClient.get(`executives/total-cost/total-cost/${rut}`);
}

const getExpectedAmountOfClientByRutForTotalCost = (rut) => {
    return httpClient.get(`executives/total-cost/${rut}/amount`);
}

const getInteresRateOfClientByRutForTotalCost = (rut) => {
    return httpClient.get(`executives/total-cost/${rut}/interest`);
}

const getTimeLimitOfClientByRutForTotalCost = (rut) => {
    return httpClient.get(`executives/total-cost/${rut}/time`);
}

const getMonthlyLoanOfClientByRutForTotalCost = (rut) => {
    return httpClient.get(`executives/total-cost/${rut}/monthly-loan`);
}




export default { 
    getExpectedAmountOfClientByRutForEvaluation,
    getInteresRateOfClientByRutForEvaluation,
    getTimeLimitOfClientByRutForEvaluation,
    getMonthlyLoanOfClientByRutForEvaluation,
    getClientByRutForEvaluation,
    getDebtsByRutForEvaluation,
    getEmploymentHistoryByRutForEvaluation,
    getBankAccountByRutForEvaluation,
    getDepositInBankAccountByRutForEvaluation,
    getWithdrawalInBankAccountByRutForEvaluation,
    getLoanTypeByRutForEvaluation,
    getCreditApplicationsByRutForFollowRequest,
    getCreditApplicationByIdForFollowRequest,
    getFeeIncomeRatioByRutForEvaluation,
    getPendingDebtsByRutForEvaluation,
    getPendingDebtsMonthlySalaryByRutForEvaluation,
    verifyClientAgeForEvaluation,
    validateBankAccountConsistencyByRutForEvaluation,
    checkPeriodicDepositsForEvaluation,
    checkLargeWithdrawalsForEvaluation,
    getExpectedAmountOfClientByRutForSimulation,
    getInteresRateOfClientByRutForSimulation,
    getTimeLimitOfClientByRutForSimulation,
    getMonthlyLoanOfClientByRutForSimulation,
    getClientByRutForSimulation,
    insuranceCalculationByRutForTotalCost,
    administrationCommissionByRutForTotalCost,
    monthlyCostByRutForTotalCost,
    totalCostOfLoanByRutForTotalCost,
    getExpectedAmountOfClientByRutForTotalCost,
    getInteresRateOfClientByRutForTotalCost,
    getTimeLimitOfClientByRutForTotalCost,
    getMonthlyLoanOfClientByRutForTotalCost,
    updateCreditApplicationStatusForFollowRequest,
    getMonthlyLoanOfClientByRutForApplication,
    getExpectedAmountOfClientByRutForApplication,
    getInteresRateOfClientByRutForApplication,
    getTimeLimitOfClientByRutForApplication
    
};