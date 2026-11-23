# Power Platform Autobots

Reference samples for the automation projects on my portfolio. Every sample uses **mock data only**. No real URLs, keys, Aadhaar numbers or client names belong in this repo.

| Folder | What it shows |
|---|---|
| 01-cloud-to-desktop | Java app triggers a Power Automate cloud flow over HTTP, which runs a desktop flow |
| 02-bulk-ticket-excel | Excel input, validation, duplicate check, logging |
| 03-firewall-rule-automation | IP validation, approval, audit log |
| 04-sharepoint-broadband-subscriptions | Department match and fee calculation by speed and GB |
| 05-kyc-loan-agent | Copilot Studio agent, Power Automate checks, CIBIL decision |
| docs | Error-handling pattern used across flows |

These are reference snippets, not importable solutions. To publish your real flows, export the solution and unpack it:
`pac solution unpack --zipfile MySolution.zip --folder ./solution`
Remove connection references, secrets and tenant details before committing.
