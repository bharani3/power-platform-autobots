# Bulk ticket creation from Excel

Flow outline
1. Trigger: file created in a SharePoint library (or manual).
2. **List rows present in a table** (Excel Online).
3. **Filter array** to drop invalid rows (see expressions below).
4. **Apply to each** valid row: duplicate check, then create the ticket.
5. Write a result row (Created, Duplicate, Invalid) to a log list.

## Expressions
Row is valid (Filter array, advanced mode):
`@and(not(empty(item()?['Summary'])), contains(createArray('Low','Medium','High'), item()?['Priority']))`

Build a duplicate key:
`@toLower(concat(trim(item()?['Requester']), '|', trim(item()?['Summary'])))`

Duplicate check: query the log list or ticket system by that key. Create the ticket only when the result count is 0:
`@equals(length(outputs('Get_items')?['body/value']), 0)`
