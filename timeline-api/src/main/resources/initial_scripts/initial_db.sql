create table if not exists users
(
    id         varchar(255) not null
    constraint users_pk
    primary key,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    email      varchar(255) not null
    );

alter table users
    owner to postgres;

create unique index if not exists users_id_uindex
    on users (id);

create table if not exists followers
(
    id                varchar(255) not null
    constraint followers_pk
    primary key,
    following_user_id varchar(255) not null,
    followed_user_id  varchar(255) not null
    );

alter table followers
    owner to postgres;

create index if not exists followers_followed_user_id_index
    on followers (followed_user_id);

create index if not exists followers_following_user_id_index
    on followers (following_user_id);

create unique index if not exists followers_id_uindex
    on followers (id);

create table if not exists tweets
(
    id           varchar(255) not null
    constraint tweets_pk
    primary key,
    user_id      varchar(255) not null,
    body         varchar(140) not null,
    published_at timestamp    not null
    );

alter table tweets
    owner to postgres;

create unique index if not exists tweets_id_uindex
    on tweets (id);

create table if not exists timeline_activities
(
    id                 varchar(255) not null
    constraint timelines_pk
    primary key,
    "timelineOwnerId"  varchar(255) not null,
    tweet_id           varchar(255) not null,
    tweet_published_at timestamp    not null,
    tweet_owner_id     varchar(255) not null,
    tweet_body         varchar(140) not null,
    published_at       timestamp    not null,
    tweet_owner_name   varchar(255) not null
    );

alter table timeline_activities
    owner to postgres;

create unique index if not exists timelines_id_uindex
    on timeline_activities (id);

create index if not exists timeline_activities_tweet_owner_id_index
    on timeline_activities (tweet_owner_id);