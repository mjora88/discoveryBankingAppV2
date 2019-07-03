Select c.TITLE, c.NAME, c.SURNAME, Table1.LOAN_AMOUNT,Table2.TRANSACTIONAL, (IFNULL(Table1.LOAN_AMOUNT,0) + IFNULL(Table2.TRANSACTIONAL ,0)) AS Net_Position from CLIENT c

LEFT JOIN (Select C.client_id As CLIENT_ID ,sum( ca.DISPLAY_BALANCE) AS LOAN_AMOUNT

FROM CLIENT c
join CLIENT_ACCOUNT ca on  c.CLIENT_ID = ca.CLIENT_ID
join ACCOUNT_TYPE AT on ca.ACCOUNT_TYPE_CODE = AT.ACCOUNT_TYPE_CODE AND AT.ACCOUNT_TYPE_CODE  IN ('PLOAN', 'HLOAN')
Group by c.CLIENT_id) as Table1
ON c.CLIENT_ID = Table1.CLIENT_ID

LEFT JOIN
(SELECT  ca.CLIENT_ID , SUM (ca.display_balance) as TRANSACTIONAL  FROM  CLIENT_ACCOUNT ca
join ACCOUNT_TYPE AT on ca.ACCOUNT_TYPE_CODE = AT.ACCOUNT_TYPE_CODE WHERE AT.transactional = 'TRUE'
GROUP BY ca.CLIENT_ID ) as Table2
ON c.CLIENT_ID = Table2.CLIENT_ID