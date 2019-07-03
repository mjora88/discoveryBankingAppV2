SELECT c.client_id, c.surname, a.client_account_number, d.description, a.display_balance
FROM client c, client_account a, account_type d
    inner join (SELECT c.client_id , MAX (a.display_balance) as HIGHEST
    FROM client c, client_account a
    WHERE c.client_id =  a.client_id
    GROUP BY c.client_id )
WHERE c.client_id = a.client_id
AND HIGHEST = a.display_balance
AND a.account_type_code = d.account_type_code