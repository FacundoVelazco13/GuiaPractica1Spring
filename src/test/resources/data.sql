-- shouldResturnExistingAlumnoById DATA
INSERT INTO public.alumno (id, legajo, nombre) VALUES (100, '9999', 'AlumnoExistente');

-- shouldUpdateAlumno DATA
INSERT INTO public.alumno (id, legajo, nombre) VALUES (101, '25420', 'AlumnoSinActualizar');

-- shouldResturnExistingDocenteById DATA
INSERT INTO public.docente (id, salario, nombre) VALUES (100, 9999.9, 'DocenteExistente');

-- shouldUpdateDocente DATA
INSERT INTO public.docente (id, salario, nombre) VALUES (101, 9999.9, 'DocenteSinActualizar');