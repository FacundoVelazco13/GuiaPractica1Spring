-- shouldResturnExistingAlumnoById DATA
INSERT INTO public.alumno (id, legajo, nombre) VALUES (100, '9999', 'AlumnoExistente');

-- shouldUpdateAlumno DATA
INSERT INTO public.alumno (id, legajo, nombre) VALUES (101, '25420', 'AlumnoSinActualizar');

-- shouldResturnExistingDocenteById DATA
INSERT INTO public.docente (id, salario, nombre) VALUES (100, 9999.9, 'DocenteExistente');

-- shouldUpdateDocente DATA
INSERT INTO public.docente (id, salario, nombre) VALUES (101, 9999.9, 'DocenteSinActualizar');

--shouldReturnExistingCursoById DATA
INSERT INTO public.curso (creditos, cupo, docente_asignado_id, id, nombre) VALUES (12, 12, 100, 100, 'CursoExistente');

-- shouldUpdateCurso DATA
INSERT INTO public.curso (creditos, cupo, docente_asignado_id, id, nombre) VALUES (12, 12, 100, 101, 'CursoSinActualizar');

-- romper todo
INSERT INTO public.curso_alumno (alumno_id, curso_id) VALUES (100, 100)