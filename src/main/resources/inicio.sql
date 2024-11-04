drop user userdata;
drop database gestorsupermark;

create user userdata with password  '123456';
create database gestorsupermark owner userdata;