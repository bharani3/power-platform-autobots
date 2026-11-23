# Firewall rule automation

1. Read IP addresses from an Excel table.
2. Validate each address and drop invalid ones into the log.
3. Send the valid list for **approval** (Start and wait for an approval).
4. On approval, call the firewall or change-management API (placeholder step) and log every result.

## IPv4 check
Power Fx (canvas app or Copilot Studio):
`IsMatch(ip, "^((25[0-5]|2[0-4][0-9]|1?[0-9]?[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1?[0-9]?[0-9])$")`

Quick check inside a flow (4 parts):
`@equals(length(split(item()?['IP'], '.')), 4)`

Log columns: RequestId, IP, Result, Approver, Timestamp (`utcNow()`).
Use only documentation-range IPs in samples (192.0.2.x).
