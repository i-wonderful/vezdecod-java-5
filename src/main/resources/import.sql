
INSERT INTO public.category (id, name)
VALUES (1, 'Main category');

INSERT INTO public.question (id, difficulty, question, category_id)
VALUES (1, 20, 'Третья планета от Солнца', 1),
       (2, 30, 'Столица Мадагаскара', 1);

INSERT INTO public.answer (id, iscorrect, value, question_id)
VALUES (1, false, 'Венера', 1),
       (2, false, 'Юпитер', 1),
       (3, true, 'Земля', 1),
       (4, false, 'Марс', 1),
       (5, false, 'Париж', 2),
       (6, false, 'Кейп-Таун', 2),
       (7, true, 'Антананариву', 2);
