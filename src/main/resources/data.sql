insert into account (id, username, password, email, is_disable, is_locked, created_date, updated_date, image_url, role)
values ('eST4k1Y5o1g5CDHek9wGSjbyfFA3', 'admin', '1', 'thuynvuanh2412@gmail.com', false, false,
        '2023-01-15', '2022-07-19', '', 0);
insert into account (id, username, password, email, is_disable, is_locked, created_date, updated_date, image_url, role)
values ('Uy3z8txHALNF43J9V1hXxlX33Os2', 'worker', '2', 'taictse161569@fpt.edu.vn', true, false,
        '2022-02-10', '2022-12-16', '', 1);

insert into wallet (id, balance, account_id)
values ('4e4df084-03c7-489c-b054-69799392ba1c', 1248.73, 'eST4k1Y5o1g5CDHek9wGSjbyfFA3');
insert into wallet (id, balance, account_id)
values ('9f5d9dd0-bfe4-4710-a409-99718ce6e9ab', 7972.92, 'Uy3z8txHALNF43J9V1hXxlX33Os2');
