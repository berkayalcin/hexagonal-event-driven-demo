INSERT INTO followers
SELECT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring),
       U.id  as following_user_id,
       U2.id as followed_user_id
FROM users U
         JOIN users U2 on U.email != U2.email
WHERE U.email='user_email_to_follow@user.email'