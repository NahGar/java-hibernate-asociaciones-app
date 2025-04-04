
INSERT INTO clientes (id,nombre,apellido,forma_pago,creado_en,editado_en) VALUES (1,'Andrés','Rodríguez','debito',NULL,NULL),(2,'Micaela','Nieves','credito',NULL,NULL),(3,'Evan','García','paypal',NULL,NULL),(4,'Gael','García','debito',NULL,NULL),(6,'Evan','ddd','credito',NULL,NULL),(8,'Pepe','Mujica','abcd','2025-03-24 10:34:20','2025-03-24 10:34:45');
INSERT INTO alumnos (id, nombre, apellido) VALUES (1, "Carlos", "Rodriguez");
INSERT INTO alumnos (id, nombre, apellido) VALUES (2, "Jazmin", "Martinez");
INSERT INTO cursos (id, titulo, profesor) VALUES (1, "Curso C#", "Montiel");
INSERT INTO cursos (id, titulo, profesor) VALUES (2, "Curso VB6", "Marcos");
INSERT INTO direcciones (calle, numero) VALUES ("Surraco", "1966");
INSERT INTO direcciones (calle, numero) VALUES ("Pedro Vidal", "1090");
INSERT INTO clientes_direcciones (id_cliente, id_direccion) VALUES (1,1);
INSERT INTO clientes_direcciones (id_cliente, id_direccion) VALUES (1,2);
INSERT INTO clientes_detalles (prime, puntos_acumulados, id_cliente) VALUES (1,800,1);
INSERT INTO alumnos_cursos (id_alumno, id_curso) VALUES (1,1);
INSERT INTO alumnos_cursos (id_alumno, id_curso) VALUES (1,2);
INSERT INTO facturas (descripcion, total, id_cliente) VALUES ('oficina',4000,1);
INSERT INTO facturas (descripcion, total, id_cliente) VALUES ('casa',5000,1);
INSERT INTO facturas (descripcion, total, id_cliente) VALUES ('deporte',3000,1);
INSERT INTO facturas (descripcion, total, id_cliente) VALUES ('computación',9000,2);