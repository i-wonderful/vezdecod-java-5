INSERT INTO public.category (id, name)
VALUES (1, 'Main category');

INSERT INTO public.question (id, difficulty, question, category_id)
VALUES (1, 20, 'Третья планета от Солнца', 1),
       (2, 30, 'Столица Мадагаскара', 1),
       (3, 100, 'Главный вопрос жизни, вселенной, и вообще', 1);

INSERT INTO public.answer (id, iscorrect, value, question_id)
VALUES (1, false, 'Венера', 1),
       (2, false, 'Юпитер', 1),
       (3, true, 'Земля', 1),
       (4, false, 'Марс', 1),
       (5, false, 'Париж', 2),
       (6, false, 'Кейп-Таун', 2),
       (7, true, 'Антананариву', 2),
       (8, true, '42', 3),
       (9, true, '43', 3);

INSERT INTO public.game (id, maxdifficulty, mindifficulty)
VALUES (1, 564, 2);

INSERT INTO public.game_question (game_id, questions_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);

INSERT INTO public.user_answer ( id, iscorrect, game_id, question_id)
VALUES ( 1, false , 1, 1 );
INSERT INTO public.user_answer ( id, iscorrect, game_id, question_id)
VALUES ( 2, true , 1, 2 );