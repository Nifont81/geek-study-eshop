INSERT INTO `categories` (`title`)
    VALUE ('cpu'), ('mb'), ('ram'), ('mouse');
GO

INSERT INTO `products` (`title`, `description`, `price`, `category_id`) VALUE
    ('cpu', 'i5', 10000,1),
    ('cpu1', 'i7', 23000,1),
    ('cpu2', 'i9', 31000,1),
    ('mb', 'msi 370', 9000,2),
    ('mb2', 'msi 390', 11000,2),
    ('ram', '16 Gb', 8000,3),
    ('ram2', '8 Gb', 4000,3);
GO
