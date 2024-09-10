CREATE TABLE "WEB5"."tbl_department"(
	"department_id" NUMBER PRIMARY KEY,
	"department_name" VARCHAR(20) UNIQUE
)

CREATE TABLE "WEB5"."tbl_address"(
	"address_id" NUMBER PRIMARY KEY,
	"address_postal_code" VARCHAR2(8),
	"address_line1" VARCHAR2(50),
	"address_line2" VARCHAR2(50)
)


CREATE TABLE "WEB5"."tbl_student"(
	"student_id" NUMBER PRIMARY KEY,
	"student_name" VARCHAR2(50),
	"student_age" NUMBER,
	"department_id" NUMBER,
	"address_id" NUMBER
);

ALTER TABLE "tbl_student" ADD CONSTRAINT FK_DEP FOREIGN KEY("department_id") REFERENCES "tbl_department"("department_id");

ALTER TABLE "tbl_student" ADD CONSTRAINT FK_ADR FOREIGN KEY("address_id") REFERENCES "tbl_address"("address_id");