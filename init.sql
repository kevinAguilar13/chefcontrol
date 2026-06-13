-- ============================================================
--  ChefControl — Script SQL completo para PostgreSQL
--  Base de datos: db_chefcontrol
--  Generado: 2026-06-13
-- ============================================================

-- ============================================================
-- 1. TIPOS ENUMERADOS
-- ============================================================

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'estado_mesa') THEN
        CREATE TYPE estado_mesa AS ENUM ('DISPONIBLE', 'OCUPADA', 'RESERVADA', 'FUERA_DE_SERVICIO');
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'rol_empleado') THEN
        CREATE TYPE rol_empleado AS ENUM ('MESERO', 'COCINERO', 'CAJERO', 'ADMINISTRADOR', 'BARTENDER');
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'estado_pedido') THEN
        CREATE TYPE estado_pedido AS ENUM ('PENDIENTE', 'EN_PREPARACION', 'LISTO', 'ENTREGADO', 'CANCELADO');
    END IF;
END $$;

-- ============================================================
-- 2. TABLAS PRINCIPALES
-- ============================================================

-- Configuración del restaurante
CREATE TABLE IF NOT EXISTS configuraciones_restaurantes (
    configuraciones_restaurantes_id         SERIAL PRIMARY KEY,
    configuraciones_restaurantes_nombre     VARCHAR(200),
    configuraciones_restaurantes_telefono   VARCHAR(30),
    configuraciones_restaurantes_direccion  TEXT,
    configuraciones_restaurantes_pedidos_habilitados BOOLEAN DEFAULT TRUE,
    configuraciones_restaurantes_hora_inicio TIME,
    configuraciones_restaurantes_hora_fin   TIME,
    configuraciones_restaurantes_max_mesas  INTEGER
);

-- Categorías de productos
CREATE TABLE IF NOT EXISTS categorias (
    categorias_id          SERIAL PRIMARY KEY,
    categorias_nombre      VARCHAR(100) NOT NULL,
    categorias_descripcion TEXT,
    categorias_activa      BOOLEAN DEFAULT TRUE
);

-- Productos
CREATE TABLE IF NOT EXISTS productos (
    productos_id                  SERIAL PRIMARY KEY,
    productos_categoria_id        INTEGER REFERENCES categorias(categorias_id),
    productos_nombre              VARCHAR(150) NOT NULL,
    productos_descripcion         TEXT,
    productos_precio              NUMERIC(10, 2) NOT NULL,
    productos_tiempo_preparacion  INTEGER,
    productos_disponible          BOOLEAN DEFAULT TRUE,
    productos_imagen_url          TEXT
);

-- Tipos de pedido
CREATE TABLE IF NOT EXISTS tipos_pedidos (
    tipos_pedidos_id     SERIAL PRIMARY KEY,
    tipos_pedidos_nombre VARCHAR(100) NOT NULL
);

-- Mesas
CREATE TABLE IF NOT EXISTS mesas (
    mesas_id        SERIAL PRIMARY KEY,
    mesas_numero    INTEGER NOT NULL,
    mesas_capacidad INTEGER NOT NULL,
    mesas_estado    VARCHAR(30) NOT NULL DEFAULT 'DISPONIBLE',
    mesas_activa    BOOLEAN DEFAULT TRUE,
    CONSTRAINT chk_mesas_estado CHECK (mesas_estado IN ('DISPONIBLE', 'OCUPADA', 'RESERVADA', 'FUERA_DE_SERVICIO'))
);

-- Clientes
CREATE TABLE IF NOT EXISTS clientes (
    clientes_id       SERIAL PRIMARY KEY,
    clientes_nombre   VARCHAR(150) NOT NULL,
    clientes_telefono VARCHAR(20),
    clientes_email    VARCHAR(150),
    clientes_activo   BOOLEAN DEFAULT TRUE
);

-- Empleados
CREATE TABLE IF NOT EXISTS empleados (
    empleados_id       SERIAL PRIMARY KEY,
    empleados_nombre   VARCHAR(150) NOT NULL,
    empleados_rol      VARCHAR(30) NOT NULL,
    empleados_telefono VARCHAR(20),
    empleados_email    VARCHAR(150),
    empleados_activo   BOOLEAN DEFAULT TRUE,
    CONSTRAINT chk_empleados_rol CHECK (empleados_rol IN ('MESERO', 'COCINERO', 'CAJERO', 'ADMINISTRADOR', 'BARTENDER'))
);

-- Pedidos
CREATE TABLE IF NOT EXISTS pedidos (
    pedidos_id              SERIAL PRIMARY KEY,
    pedidos_mesa_id         INTEGER REFERENCES mesas(mesas_id),
    pedidos_cliente_id      INTEGER REFERENCES clientes(clientes_id),
    pedidos_tipo_pedido_id  INTEGER REFERENCES tipos_pedidos(tipos_pedidos_id),
    pedidos_empleado_id     INTEGER REFERENCES empleados(empleados_id),
    pedidos_estado          VARCHAR(30) NOT NULL DEFAULT 'PENDIENTE',
    pedidos_fecha_hora      TIMESTAMP NOT NULL DEFAULT NOW(),
    pedidos_total           NUMERIC(10, 2) DEFAULT 0.00,
    pedidos_observaciones   TEXT,
    CONSTRAINT chk_pedidos_estado CHECK (pedidos_estado IN ('PENDIENTE', 'EN_PREPARACION', 'LISTO', 'ENTREGADO', 'CANCELADO'))
);

-- Detalles de pedidos
CREATE TABLE IF NOT EXISTS detalles_pedidos (
    detalles_pedidos_id              SERIAL PRIMARY KEY,
    detalles_pedidos_pedido_id       INTEGER NOT NULL REFERENCES pedidos(pedidos_id) ON DELETE CASCADE,
    detalles_pedidos_producto_id     INTEGER NOT NULL REFERENCES productos(productos_id),
    detalles_pedidos_cantidad        INTEGER NOT NULL,
    detalles_pedidos_precio_unitario NUMERIC(10, 2) NOT NULL,
    detalles_pedidos_subtotal        NUMERIC(10, 2),
    detalles_pedidos_observaciones   TEXT
);

-- ============================================================
-- 3. ÍNDICES
-- ============================================================

CREATE INDEX IF NOT EXISTS idx_productos_categoria ON productos(productos_categoria_id);
CREATE INDEX IF NOT EXISTS idx_productos_disponible ON productos(productos_disponible);
CREATE INDEX IF NOT EXISTS idx_mesas_estado ON mesas(mesas_estado);
CREATE INDEX IF NOT EXISTS idx_pedidos_estado ON pedidos(pedidos_estado);
CREATE INDEX IF NOT EXISTS idx_pedidos_mesa ON pedidos(pedidos_mesa_id);
CREATE INDEX IF NOT EXISTS idx_pedidos_fecha ON pedidos(pedidos_fecha_hora);
CREATE INDEX IF NOT EXISTS idx_detalles_pedido ON detalles_pedidos(detalles_pedidos_pedido_id);

-- ============================================================
-- 4. DATOS DE EJEMPLO (SEED DATA)
-- ============================================================

-- Configuración del restaurante
INSERT INTO configuraciones_restaurantes (
    configuraciones_restaurantes_nombre,
    configuraciones_restaurantes_telefono,
    configuraciones_restaurantes_direccion,
    configuraciones_restaurantes_pedidos_habilitados,
    configuraciones_restaurantes_hora_inicio,
    configuraciones_restaurantes_hora_fin,
    configuraciones_restaurantes_max_mesas
) VALUES (
    'ChefControl Restaurante',
    '+1-555-0100',
    'Av. Principal 123, Ciudad',
    TRUE,
    '08:00:00',
    '22:00:00',
    20
) ON CONFLICT DO NOTHING;

-- Categorías
INSERT INTO categorias (categorias_nombre, categorias_descripcion, categorias_activa) VALUES
    ('Entradas',     'Aperitivos y entradas',           TRUE),
    ('Platos Fuertes', 'Platos principales del menú',   TRUE),
    ('Postres',      'Postres y dulces',                TRUE),
    ('Bebidas',      'Bebidas frías y calientes',       TRUE),
    ('Bebidas Alcohólicas', 'Cervezas, vinos y cocteles', TRUE)
ON CONFLICT DO NOTHING;

-- Productos
INSERT INTO productos (productos_categoria_id, productos_nombre, productos_descripcion, productos_precio, productos_tiempo_preparacion, productos_disponible) VALUES
    (1, 'Nachos con Queso',     'Totopos con queso fundido y jalapeños',      8.50,  10, TRUE),
    (1, 'Alitas BBQ',           'Alitas de pollo con salsa BBQ',             10.00,  15, TRUE),
    (1, 'Sopa del día',         'Sopa casera según temporada',                6.00,  10, TRUE),
    (2, 'Filete de Res',        'Filete de 250g con papas y ensalada',       22.00,  25, TRUE),
    (2, 'Pollo a la Plancha',   'Pechuga de pollo con vegetales salteados',  14.00,  20, TRUE),
    (2, 'Pasta Carbonara',      'Pasta con salsa cremosa y tocino',          12.00,  15, TRUE),
    (2, 'Hamburguesa Clásica',  'Carne de res con lechuga, tomate y queso',  11.00,  12, TRUE),
    (3, 'Cheesecake',           'Pastel de queso con frutos rojos',           6.50,   5, TRUE),
    (3, 'Helado 3 sabores',     'Helado de vainilla, chocolate y fresa',      4.00,   3, TRUE),
    (4, 'Agua Natural',         'Agua purificada 600ml',                      2.00,   1, TRUE),
    (4, 'Refresco',             'Bebida gaseosa 355ml',                       3.00,   1, TRUE),
    (4, 'Jugo Natural',         'Jugo de fruta fresca del día',               4.50,   5, TRUE),
    (4, 'Café Americano',       'Café negro americano',                       3.50,   5, TRUE),
    (5, 'Cerveza Nacional',     'Cerveza de botella 355ml',                   4.00,   2, TRUE),
    (5, 'Vino Tinto Copa',      'Copa de vino tinto de la casa',              6.00,   2, TRUE)
ON CONFLICT DO NOTHING;

-- Tipos de pedido
INSERT INTO tipos_pedidos (tipos_pedidos_nombre) VALUES
    ('En Mesa'),
    ('Para Llevar'),
    ('Delivery'),
    ('Bar')
ON CONFLICT DO NOTHING;

-- Mesas
INSERT INTO mesas (mesas_numero, mesas_capacidad, mesas_estado, mesas_activa) VALUES
    (1,  2, 'DISPONIBLE', TRUE),
    (2,  2, 'DISPONIBLE', TRUE),
    (3,  4, 'DISPONIBLE', TRUE),
    (4,  4, 'DISPONIBLE', TRUE),
    (5,  4, 'DISPONIBLE', TRUE),
    (6,  6, 'DISPONIBLE', TRUE),
    (7,  6, 'DISPONIBLE', TRUE),
    (8,  8, 'DISPONIBLE', TRUE),
    (9,  8, 'DISPONIBLE', TRUE),
    (10, 10,'DISPONIBLE', TRUE)
ON CONFLICT DO NOTHING;

-- Empleados
INSERT INTO empleados (empleados_nombre, empleados_rol, empleados_telefono, empleados_email, empleados_activo) VALUES
    ('Carlos Mendoza',   'ADMINISTRADOR', '+1-555-0201', 'carlos@chefcontrol.com',  TRUE),
    ('Ana García',       'CAJERO',        '+1-555-0202', 'ana@chefcontrol.com',     TRUE),
    ('Luis Rodríguez',   'MESERO',        '+1-555-0203', 'luis@chefcontrol.com',    TRUE),
    ('María Fernández',  'MESERO',        '+1-555-0204', 'maria@chefcontrol.com',   TRUE),
    ('Pedro Ramírez',    'COCINERO',      '+1-555-0205', 'pedro@chefcontrol.com',   TRUE),
    ('Sofía Torres',     'COCINERO',      '+1-555-0206', 'sofia@chefcontrol.com',   TRUE),
    ('Diego Vargas',     'BARTENDER',     '+1-555-0207', 'diego@chefcontrol.com',   TRUE)
ON CONFLICT DO NOTHING;

-- Clientes de ejemplo
INSERT INTO clientes (clientes_nombre, clientes_telefono, clientes_email, clientes_activo) VALUES
    ('Juan Pérez',      '+1-555-0301', 'juan.perez@email.com',    TRUE),
    ('Laura Sánchez',   '+1-555-0302', 'laura.s@email.com',       TRUE),
    ('Roberto Díaz',    '+1-555-0303', 'roberto.d@email.com',     TRUE)
ON CONFLICT DO NOTHING;

-- ============================================================
-- FIN DEL SCRIPT
-- ============================================================
