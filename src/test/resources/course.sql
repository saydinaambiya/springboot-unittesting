
INSERT INTO mst_course_type(course_type_id, type_name)
VALUES ('1', 'Dummy type 1');

INSERT INTO mst_course_info(course_info_id, duration, level)
VALUES ('1', '100', 'Beginner');

INSERT INTO mst_course(course_id, title, description, link, course_info_id, course_type_id)
VALUES ('1', 'dummy title 1', 'dummy description 1', 'dummy link 1', '1', '1');
INSERT INTO mst_course(course_id, title, description, link, course_info_id, course_type_id)
VALUES ('2', 'dummy title 2', 'dummy description 2', 'dummy link 2', '1', '1');



