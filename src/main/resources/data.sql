insert into Account (id, username, password, email, is_disable, is_locked, created_date, updated_date)
values ('bab776f1-30b8-444d-9eb9-41f918116e3e', 'ggarriock0', '18kr77jZTP', 'dcrosser0@hostgator.com', false, true,
        '2023-01-15', '2022-07-19');
insert into Account (id, username, password, email, is_disable, is_locked, created_date, updated_date)
values ('6b074abd-16dd-454c-936c-280a0d98852a', 'fdaintry1', 'Z1UmAQkTjC', 'etills1@harvard.edu', true, true,
        '2022-02-10', '2022-12-16');
insert into Account (id, username, password, email, is_disable, is_locked, created_date, updated_date)
values ('dd83b0b2-bb91-422c-a79a-2ecc10b17d01', 'bimm2', '9CIOZV1ezkl', 'amoorfield2@cbslocal.com', false, true,
        '2022-06-16', '2022-09-24');

insert into wallet (id, balance, account_id)
values ('4e4df084-03c7-489c-b054-69799392ba1c', 1248.73, 'bab776f1-30b8-444d-9eb9-41f918116e3e');
insert into wallet (id, balance, account_id)
values ('9f5d9dd0-bfe4-4710-a409-99718ce6e9ab', 7972.92, '6b074abd-16dd-454c-936c-280a0d98852a');
# insert into wallet (id, balance, account_id)
# values ('4b42115c-ca78-4f31-a0f4-b3b8c70519f4', 4924.06, 'dd83b0b2-bb91-422c-a79a-2ecc10b17d01');