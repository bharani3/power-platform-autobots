# SharePoint broadband subscriptions

SharePoint list `Subscriptions` (see `subscriptions-columns.csv`).

Flow outline
1. Trigger: item created or modified in `Subscriptions`.
2. **Get items** from the `Departments` list filtered by `DeptCode`.
3. If no match, set Status = "Invalid department" and notify the owner.
4. Calculate the fee and **Update item**.

## Fee expression (rates are illustrative)
`@add(mul(triggerOutputs()?['body/SpeedMbps'], 2.5), mul(triggerOutputs()?['body/DataGB'], 0.8))`

Avoid update loops: add a trigger condition such as
`@not(equals(triggerOutputs()?['body/Status/Value'], 'Fee updated'))`
and set Status = "Fee updated" when the flow finishes.
