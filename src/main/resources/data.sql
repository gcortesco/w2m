DROP TABLE IF EXISTS superhero;

CREATE TABLE superhero (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
);

INSERT INTO superhero (name) VALUES
  ('Superman'),
  ('Batman'),
  ('Aquaman'),
  ('Spiderman');
