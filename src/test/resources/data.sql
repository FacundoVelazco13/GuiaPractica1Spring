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
INSERT INTO public.curso_alumno (alumno_id, curso_id) VALUES (100, 100);

-- shouldAllowAddDocenteToCurso DATA
INSERT INTO public.docente (id, salario, nombre) VALUES (90, 123.45, 'DocenteParaCurso');
INSERT INTO public.curso (creditos, cupo, docente_asignado_id, id, nombre) VALUES (12, 12, null, 90, 'CursoParaAñadir1');
INSERT INTO public.curso (creditos, cupo, docente_asignado_id, id, nombre) VALUES (12, 12, null, 91, 'CursoParaAñadir2');
INSERT INTO public.curso (creditos, cupo, docente_asignado_id, id, nombre) VALUES (12, 12, null, 92, 'CursoParaAñadir3');
-- shouldNotAllowAddDocenteToCurso DATA
INSERT INTO public.curso (creditos, cupo, docente_asignado_id, id, nombre) VALUES (12, 12, null, 93, 'CursoParaExceder1');
INSERT INTO public.curso (creditos, cupo, docente_asignado_id, id, nombre) VALUES (12, 12, null, 94, 'CursoParaExceder2');

--shouldAllowEnrollStudentInCourse DATA
--shouldNotAllowEnrollStudentInCourse DATA