create table catching_word
(
    catching_word_id   bigint      not null auto_increment,
    created_date       datetime(6) not null,
    last_modified_date datetime(6) not null,
    word               varchar(45),
    primary key (catching_word_id)
);

create table catching_word_answer
(
    catching_word_answer_id bigint not null auto_increment,
    created_date datetime(6) not null,
    last_modified_date datetime(6) not null,
    answer varchar(45) not null,
    created_by varchar(45) not null,
    ui_mode varchar(25) not null,
    catching_word_id bigint not null,
    primary key (catching_word_answer_id)
);

create table finding_word
(
    finding_word_id    bigint        not null auto_increment,
    created_date       datetime(6)   not null,
    last_modified_date datetime(6)   not null,
    correct_answer     integer       not null,
    text               varchar(1000) not null,
    word               varchar(45)   not null,
    primary key (finding_word_id)
);

create table finding_word_answer
(
    finding_word_answer_id bigint not null auto_increment,
    created_date datetime(6) not null,
    last_modified_date datetime(6) not null,
    answer integer not null,
    created_by varchar(45) not null,
    estimated_seconds integer not null,
    ui_mode varchar(25) not null,
    finding_word_id bigint not null,
    primary key (finding_word_answer_id)
);

create table ui_preference_survey
(
    ui_preference_survey_id bigint      not null auto_increment,
    ui_mode                 varchar(25) not null,
    created_date            datetime(6) not null,
    last_modified_date      datetime(6) not null,
    primary key (ui_preference_survey_id)
);

alter table catching_word_answer
    add constraint FK_catching_word_answer_catching_word
        foreign key (catching_word_id)
            references catching_word (catching_word_id);

alter table finding_word_answer
    add constraint FK_finding_word_answer_finding_word
        foreign key (finding_word_id)
            references finding_word (finding_word_id);