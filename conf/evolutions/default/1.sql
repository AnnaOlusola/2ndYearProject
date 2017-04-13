# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table appointment (
  appointment_id                varchar(255) not null,
  time                          time,
  date                          timestamp,
  member_name                   varchar(255),
  member_id                     varchar(255),
  staff_name                    varchar(255),
  admin_email                   varchar(255),
  member_email                  varchar(255),
  constraint pk_appointment primary key (appointment_id)
);

create table blog_post (
  id                            bigint not null,
  title                         varchar(255),
  category                      varchar(255),
  text                          TEXT,
  posted                        timestamp,
  constraint pk_blog_post primary key (id)
);
create sequence blog_post_seq;

create table booking_item (
  id                            bigint not null,
  order_id                      bigint,
  basket_id                     bigint,
  time_slot_id                  bigint,
  quantity                      integer,
  constraint pk_booking_item primary key (id)
);
create sequence booking_item_seq;

create table booking_order (
  id                            bigint not null,
  booking_date                  timestamp,
  member_email                  varchar(255),
  constraint pk_booking_order primary key (id)
);
create sequence booking_order_seq;

create table diet (
  id                            varchar(255) not null,
  src                           varchar(255),
  constraint pk_diet primary key (id)
);

create table progress (
  date                          timestamp not null,
  height                        double,
  weight                        double,
  waist                         double,
  thigh                         double,
  member_email                  varchar(255),
  constraint pk_progress primary key (date)
);

create table time_slot (
  id                            bigint not null,
  date                          timestamp,
  day                           varchar(255),
  time                          varchar(255),
  trainer                       varchar(255),
  duration                      varchar(255),
  places                        integer,
  constraint pk_time_slot primary key (id)
);
create sequence time_slot_seq;

create table user (
  role                          varchar(255),
  email                         varchar(255) not null,
  first_name                    varchar(255),
  last_name                     varchar(255),
  password                      varchar(255),
  address_line1                 varchar(255),
  address_line2                 varchar(255),
  address_line3                 varchar(255),
  phone_number                  varchar(255),
  medical_condition             varchar(255),
  doctors_name                  varchar(255),
  doctors_phone                 varchar(255),
  emergency_name                varchar(255),
  emergency_phone               varchar(255),
  relationship                  varchar(255),
  security_question             varchar(255),
  security_answer               varchar(255),
  constraint pk_user primary key (email)
);

create table video (
  id                            varchar(255) not null,
  url                           varchar(255),
  constraint pk_video primary key (id)
);

create table your_booking (
  id                            bigint not null,
  member_email                  varchar(255),
  constraint uq_your_booking_member_email unique (member_email),
  constraint pk_your_booking primary key (id)
);
create sequence your_booking_seq;

alter table appointment add constraint fk_appointment_admin_email foreign key (admin_email) references user (email) on delete restrict on update restrict;
create index ix_appointment_admin_email on appointment (admin_email);

alter table appointment add constraint fk_appointment_member_email foreign key (member_email) references user (email) on delete restrict on update restrict;
create index ix_appointment_member_email on appointment (member_email);

alter table booking_item add constraint fk_booking_item_order_id foreign key (order_id) references booking_order (id) on delete restrict on update restrict;
create index ix_booking_item_order_id on booking_item (order_id);

alter table booking_item add constraint fk_booking_item_basket_id foreign key (basket_id) references your_booking (id) on delete restrict on update restrict;
create index ix_booking_item_basket_id on booking_item (basket_id);

alter table booking_item add constraint fk_booking_item_time_slot_id foreign key (time_slot_id) references time_slot (id) on delete restrict on update restrict;
create index ix_booking_item_time_slot_id on booking_item (time_slot_id);

alter table booking_order add constraint fk_booking_order_member_email foreign key (member_email) references user (email) on delete restrict on update restrict;
create index ix_booking_order_member_email on booking_order (member_email);

alter table progress add constraint fk_progress_member_email foreign key (member_email) references user (email) on delete restrict on update restrict;
create index ix_progress_member_email on progress (member_email);

alter table your_booking add constraint fk_your_booking_member_email foreign key (member_email) references user (email) on delete restrict on update restrict;


# --- !Downs

alter table appointment drop constraint if exists fk_appointment_admin_email;
drop index if exists ix_appointment_admin_email;

alter table appointment drop constraint if exists fk_appointment_member_email;
drop index if exists ix_appointment_member_email;

alter table booking_item drop constraint if exists fk_booking_item_order_id;
drop index if exists ix_booking_item_order_id;

alter table booking_item drop constraint if exists fk_booking_item_basket_id;
drop index if exists ix_booking_item_basket_id;

alter table booking_item drop constraint if exists fk_booking_item_time_slot_id;
drop index if exists ix_booking_item_time_slot_id;

alter table booking_order drop constraint if exists fk_booking_order_member_email;
drop index if exists ix_booking_order_member_email;

alter table progress drop constraint if exists fk_progress_member_email;
drop index if exists ix_progress_member_email;

alter table your_booking drop constraint if exists fk_your_booking_member_email;

drop table if exists appointment;

drop table if exists blog_post;
drop sequence if exists blog_post_seq;

drop table if exists booking_item;
drop sequence if exists booking_item_seq;

drop table if exists booking_order;
drop sequence if exists booking_order_seq;

drop table if exists diet;

drop table if exists progress;

drop table if exists time_slot;
drop sequence if exists time_slot_seq;

drop table if exists user;

drop table if exists video;

drop table if exists your_booking;
drop sequence if exists your_booking_seq;

