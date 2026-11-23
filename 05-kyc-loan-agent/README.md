# KYC and loan agent

Copilot Studio agent (front end) -> Power Automate cloud flows (background checks) -> Dataverse (cases and audit log).

## Agent topic: "Apply for loan"
Questions: full name, loan amount, Aadhaar number (validate in the topic), consent confirmation.
Do not continue unless the applicant gives **consent**.
Aadhaar validation (Power Fx): `IsMatch(Topic.Aadhaar, "^[2-9][0-9]{11}$")`

## Flow: KYC and CIBIL check (called from the agent)
Inputs: name, aadhaarRef, loanAmount, consent.
1. Mask Aadhaar and store only the masked value:
   `@concat('XXXX-XXXX-', substring(triggerBody()?['aadhaar'], 8, 4))`
2. Call the KYC provider and the credit bureau through the custom connector in `cibil-connector.swagger.yaml` (mock here).
3. Decision (thresholds are illustrative; CIBIL scores range 300 to 900):
   `@if(greaterOrEquals(int(variables('score')), 750), 'Approved', if(less(int(variables('score')), 650), 'Rejected', 'ManualReview'))`
4. ManualReview: **Start and wait for an approval** from the manager.
5. Create a Dataverse row in `LoanCase` and an audit row. Return the status to the agent.

## Dataverse tables
`LoanCase`: CaseId, ApplicantName, MaskedAadhaar, LoanAmount, CibilScore, Decision, ConsentGiven, CreatedOn
`AuditLog`: CaseId, Action, Actor, Timestamp

## Privacy notes
Never log or store the full Aadhaar number. Get consent before pulling a credit score. Limit who can read `LoanCase`.
