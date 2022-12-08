insert into mst_course_type(course_type_id, type_name)
values ('1', 'Dummy type 1');

insert into mst_course_info(course_info_id, duration, level)
values ('1', '100', 'A');

insert into mst_course(course_id, title, description, link, course_info_id, course_type_id)
values ('1', 'Dummy title 1', 'Dummy description 1', 'Dummy link 1', '1', '1');

insert into mst_course(course_id, title, description, link, course_info_id, course_type_id)
values ('2', 'Dummy title 2', 'Dummy description 2', 'Dummy link 2', '1', '1');

