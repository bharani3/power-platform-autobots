# Error handling pattern (try / catch / finally)

Use three **Scope** actions in every flow:
1. **Try**: the main actions.
2. **Catch**: configure run after = "has failed" and "has timed out" on Try. Log the error and notify.
3. **Finally**: run after = all outcomes. Close out the log row.

Useful expressions
- Error text: `@result('Try')`
- Flow run link: `@concat('https://make.powerautomate.com/environments/', workflow()?['tags']?['environmentName'], '/flows/', workflow()?['name'], '/runs/', workflow()?['run']?['name'])`
- Retry policy on HTTP actions: exponential, 3 attempts.

Also: child flows for reusable steps, a run log in SharePoint or Dataverse, and a Teams alert on failure.
