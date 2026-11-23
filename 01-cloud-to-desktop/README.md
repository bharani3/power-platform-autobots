# Cloud-to-desktop automation

Java app -> HTTP request -> Power Automate cloud flow ("When an HTTP request is received") -> Power Automate Desktop flow -> ticket created in a legacy application.

## Cloud flow steps
1. Trigger: **When an HTTP request is received** (use `request-schema.json`).
2. **Condition**: `@not(empty(triggerBody()?['summary']))`, otherwise **Response** 400.
3. **Run a flow built with Power Automate for desktop** (attended or unattended), passing summary, priority and requester.
4. **Response** 200 with a request id.
5. Wrap steps 2 and 3 in the try/catch scope pattern in `docs/error-handling.md`.

Keep the trigger URL out of source control. Store it in an environment variable or secret store.
