-- Hammadde verileri
INSERT INTO raw_materials (id, code, name, description, unit, quantity, minimum_stock, reorder_point, location, supplier, category) VALUES
(1, 'HAM001', 'Alüminyum Profil', 'Standart alüminyum profil', 'metre', 150.5, 50.0, 80.0, 'A-101', 'Metal A.Ş.', 'Metal'),
(2, 'HAM002', 'Çelik Sac', '1.5mm kalınlığında çelik sac', 'kg', 300.0, 100.0, 150.0, 'A-102', 'Metal A.Ş.', 'Metal'),
(3, 'HAM003', 'Plastik Granül', 'ABS plastik granül', 'kg', 80.0, 30.0, 50.0, 'B-201', 'Plastik Sanayi Ltd.', 'Plastik'),
(4, 'HAM004', 'Silikon Conta', 'Isıya dayanıklı silikon conta', 'metre', 45.0, 20.0, 30.0, 'B-202', 'Conta Ltd.', 'Kauçuk'),
(5, 'HAM005', 'Vida M4', 'M4 çapında 10mm uzunluğunda vida', 'adet', 2500, 500, 1000, 'C-301', 'Bağlantı Elemanları A.Ş.', 'Bağlantı Elemanı'),
(6, 'HAM006', 'Boya - Kırmızı', 'Endüstriyel kırmızı boya', 'lt', 25.0, 10.0, 15.0, 'C-302', 'Boya Kimya A.Ş.', 'Boya'),
(7, 'HAM007', 'Boya - Mavi', 'Endüstriyel mavi boya', 'lt', 8.0, 10.0, 15.0, 'C-303', 'Boya Kimya A.Ş.', 'Boya'),
(8, 'HAM008', 'Bakır Tel', '2mm çapında bakır tel', 'kg', 120.0, 40.0, 60.0, 'D-401', 'Metal A.Ş.', 'Metal'),
(9, 'HAM009', 'Elektrik Kablosu', '3x1.5mm elektrik kablosu', 'metre', 350.0, 100.0, 200.0, 'D-402', 'Elektrik Malzemeleri Ltd.', 'Elektrik'),
(10, 'HAM010', 'Isı Yalıtım Bandı', 'Yüksek ısı yalıtım bandı', 'metre', 18.0, 20.0, 40.0, 'D-403', 'Yalıtım A.Ş.', 'Yalıtım');

INSERT INTO product (code, name, price, stock, description, manufacturing_date, expiry_date)
VALUES
('IND001', 'Hidrolik Yağ', 1250.50, 100, 'Endüstriyel Hidrolik Sistem Yağı 20L', '2024-03-01', '2025-03-01'),
('IND002', 'Elektrik Motoru', 3290.90, 50, '5.5 kW Trifaze Asenkron Motor', '2024-03-05', '2029-03-05'),
('IND003', 'Kaynak Teli', 750.50, 200, 'MIG Kaynak Teli 1.2mm 15kg', '2024-03-10', '2026-03-10'),
('IND004', 'Rulman', 185.00, 30, '6205 Bilyalı Rulman', '2024-02-15', '2029-02-15'),
('IND005', 'Dişli Çark', 875.75, 120, 'Modül 3 Çelik Dişli 40 Diş', '2024-03-08', '2029-03-08'),
('IND006', 'Kompresör', 12450.90, 40, '500 Litre 10 Bar Hava Kompresörü', '2024-03-06', '2029-03-06'),
('IND007', 'Paslanmaz Çelik Boru', 325.50, 150, '304 Kalite 2 inç Paslanmaz Boru (metre)', '2024-01-10', '2029-01-10'),
('IND008', 'Konveyör Bant', 4120.00, 25, 'PVC Konveyör Bant 60cm x 10m', '2024-02-20', '2026-02-20'),
('IND009', 'Redüktör', 5890.90, 35, 'Helisel Dişli Redüktör 1/40 Oran', '2024-03-09', '2029-03-09'),
('IND010', 'Filtre Elemanı', 458.75, 60, 'Hidrolik Sistem Filtresi 10 Mikron', '2024-01-15', '2025-12-15'),
('IND011', 'Titreşim Damperi', 1625.25, 80, 'Makine Titreşim Sönümleyici Set', '2024-03-02', '2026-03-02'),
('IND012', 'Basınç Sensörü', 1220.50, 45, '0-10 Bar Analog Basınç Sensörü', '2024-03-07', '2027-03-07'),
('IND013', 'Elektrik Panosu', 3590.90, 100, '80x60x30 Metal Elektrik Panosu', '2024-02-25', '2029-02-25'),
('IND014', 'Çelik Halat', 642.00, 70, '10mm Çelik Çekme Halat (metre)', '2024-01-20', '2029-01-20'),
('IND015', 'Kaplin', 1210.50, 20, 'Elastik Kaplin 40mm', '2024-02-28', '2029-02-28'),
('IND016', 'Zincir', 1290.90, 60, 'Transmisyon Zinciri 1 inç', '2024-03-05', '2029-03-05'),
('IND017', 'PLC Kontrol Ünitesi', 9550.50, 30, 'Endüstriyel PLC 24 I/O', '2024-03-01', '2034-03-01'),
('IND018', 'Metal Toz Boya', 425.75, 120, 'Elektrostatik Toz Boya 25kg', '2024-02-10', '2025-08-10'),
('IND019', 'Keçe', 175.00, 25, 'Hidrolik Silindir Keçesi 50mm', '2024-01-05', '2027-01-05'),
('IND020', 'Endüstriyel Yapıştırıcı', 614.25, 85, 'Metal-Metal Epoksi Yapıştırıcı 5kg', '2024-03-08', '2025-03-08'),
('IND021', 'Frekans Konvertörü', 3850.50, 90, '7.5 kW 3 Faz Frekans İnvertörü', '2024-02-15', '2034-02-15'),
('IND022', 'Alüminyum Profil', 219.90, 150, '40x40 Alüminyum Sigma Profil (metre)', '2024-03-03', '2029-03-03'),
('IND023', 'Kontaktör', 865.75, 25, '65A Trifaze Kontaktör', '2024-02-20', '2029-02-20'),
('IND024', 'O-Ring Set', 365.50, 40, 'NBR O-Ring Conta Seti 400 Parça', '2024-01-25', '2027-01-25'),
('IND025', 'Paslanmaz Cıvata', 12.25, 180, 'M10x50 A2 Paslanmaz İnbus Cıvata', '2024-03-01', '2029-03-01'),
('IND026', 'Endüstriyel Temizleyici', 285.90, 35, 'Ağır Sanayi Tipi Yağ Çözücü 20L', '2023-12-15', '2025-12-15'),
('IND027', 'Pnömatik Silindir', 1428.50, 60, '50mm Çap 300mm Strok Pnömatik Silindir', '2024-01-30', '2029-01-30'),
('IND028', 'Kontrol Rölesi', 575.00, 45, '24V DC 10A Endüstriyel Röle', '2024-03-07', '2029-03-07'),
('IND029', 'Kesici Takım Seti', 4225.90, 20, 'Karbür Freze Ucu Seti 50 Parça', '2023-11-15', '2026-11-15'),
('IND030', 'Lazer Sensör', 3275.75, 70, 'Endüstriyel Mesafe Ölçüm Sensörü 0-100m', '2024-03-09', '2029-03-09'),


('PRD001', 'Elma', 15.50, 100, 'Taze Amasya Elması', '2024-03-01', '2024-03-15'),
('PRD002', 'Süt', 32.90, 50, 'Günlük Tam Yağlı Süt', '2024-03-05', '2024-03-20'),
('PRD003', 'Ekmek', 7.50, 200, 'Tam Buğday Ekmeği', '2024-03-10', '2024-03-12'),
('PRD004', 'Peynir', 185.00, 30, 'Olgunlaştırılmış Beyaz Peynir', '2024-02-15', '2024-05-15'),
('PRD005', 'Domates', 18.75, 120, 'Salkım Domates', '2024-03-08', '2024-03-18'),
('PRD006', 'Yoğurt', 45.90, 40, 'Probiyotik Yoğurt', '2024-03-06', '2024-03-26'),
('PRD007', 'Makarna', 12.50, 150, 'Kepekli Burgu Makarna', '2024-01-10', '2024-12-10'),
('PRD008', 'Zeytin', 120.00, 25, 'Gemlik Siyah Zeytin', '2024-02-20', '2024-06-20'),
('PRD009', 'Tavuk', 89.90, 35, 'Taze Bütün Tavuk', '2024-03-09', '2024-03-16'),
('PRD010', 'Pirinç', 58.75, 60, 'Baldo Pirinç', '2024-01-15', '2024-12-15'),
('PRD011', 'Portakal', 16.25, 80, 'Taze Finike Portakalı', '2024-03-02', '2024-03-16'),
('PRD012', 'Ayran', 22.50, 45, 'Taze Ayran', '2024-03-07', '2024-03-18'),
('PRD013', 'Çikolata', 35.90, 100, 'Sütlü Çikolata', '2024-02-25', '2024-07-25'),
('PRD014', 'Bakliyat', 42.00, 70, 'Kuru Fasulye', '2024-01-20', '2024-09-20'),
('PRD015', 'Sucuk', 210.50, 20, 'Fermente Sucuk', '2024-02-28', '2024-05-28'),
('PRD016', 'Muz', 29.90, 60, 'İthal Muz', '2024-03-05', '2024-03-15'),
('PRD017', 'Tereyağı', 95.50, 30, 'Köy Tereyağı', '2024-03-01', '2024-04-30'),
('PRD018', 'Un', 25.75, 120, 'Çok Amaçlı Un', '2024-02-10', '2024-08-10'),
('PRD019', 'Bal', 175.00, 25, 'Organik Çiçek Balı', '2024-01-05', '2025-01-05'),
('PRD020', 'Salatalık', 14.25, 85, 'Sera Salatalık', '2024-03-08', '2024-03-17'),
('PRD021', 'Maden Suyu', 18.50, 90, 'Sade Maden Suyu', '2024-02-15', '2024-08-15'),
('PRD022', 'Patates', 19.90, 150, 'Taze Patates', '2024-03-03', '2024-04-03'),
('PRD023', 'Kaşar Peyniri', 165.75, 25, 'Olgunlaştırılmış Kaşar', '2024-02-20', '2024-05-20'),
('PRD024', 'Reçel', 65.50, 40, 'Çilek Reçeli', '2024-01-25', '2024-11-25'),
('PRD025', 'Soğan', 12.25, 180, 'Kuru Soğan', '2024-03-01', '2024-05-01'),
('PRD026', 'Şampuan', 85.90, 35, 'Kepeğe Karşı Şampuan', '2023-12-15', '2025-12-15'),
('PRD027', 'Bulgur', 28.50, 60, 'Köftelik Bulgur', '2024-01-30', '2024-10-30'),
('PRD028', 'Yumurta', 75.00, 45, 'Organik Yumurta (15 Adet)', '2024-03-07', '2024-03-28'),
('PRD029', 'Zeytinyağı', 225.90, 20, 'Erken Hasat Sızma Zeytinyağı', '2023-11-15', '2024-11-15'),
('PRD030', 'Limon', 17.75, 70, 'Taze Limon', '2024-03-09', '2024-03-25');


-- Kalite Kontrolörleri (Inspectors)
INSERT INTO inspector (name, department, email, phone) VALUES
('Ahmet Yılmaz', 'Kalite Kontrol', 'ahmet.yilmaz@sirket.com', '555-123-4567'),
('Ayşe Kaya', 'Kalite Güvence', 'ayse.kaya@sirket.com', '555-234-5678'),
('Mehmet Demir', 'Üretim Kontrol', 'mehmet.demir@sirket.com', '555-345-6789'),
('Zeynep Çelik', 'Kalite Kontrol', 'zeynep.celik@sirket.com', '555-456-7890'),
('Mustafa Öztürk', 'Üretim Kontrol', 'mustafa.ozturk@sirket.com', '555-567-8901');

-- Kalite Kontrol Kayıtları
INSERT INTO quality_control (qc_code, status, product_id, product_code, product_name, batch_number, inspector_id, inspector_name, inspection_date, manufacturing_date, notes) VALUES
-- Onaylanmış kayıtlar
('QC2025001', 'APPROVED', 1, 'IND001', 'Hidrolik Yağ', 'BT202501', 1, 'Ahmet Yılmaz', '2025-01-05', '2024-03-01', 'Tüm testleri başarıyla geçti.'),
('QC2025002', 'APPROVED', 4, 'IND004', 'Rulman', 'BT202502', 2, 'Ayşe Kaya', '2025-01-07', '2024-02-15', 'Boyut ve dayanıklılık kriterleri karşılandı.'),
('QC2025008', 'APPROVED', 10, 'IND010', 'Filtre Elemanı', 'BT202508', 1, 'Ahmet Yılmaz', '2025-01-18', '2024-01-15', 'Filtrasyon teslerinde yüksek performans gösterdi.'),
('QC2025009', 'APPROVED', 5, 'IND005', 'Dişli Çark', 'BT202509', 3, 'Mehmet Demir', '2025-01-20', '2024-03-08', 'Hassasiyet testlerini geçti.'),
('QC2025012', 'APPROVED', 22, 'IND022', 'Alüminyum Profil', 'BT202512', 4, 'Zeynep Çelik', '2025-01-25', '2024-03-03', 'Kalınlık ve düzlük testleri başarılı.'),
('QC2025016', 'APPROVED', 33, 'PRD003', 'Ekmek', 'BT202516', 5, 'Mustafa Öztürk', '2025-02-02', '2025-02-02', 'Tat ve yapı standartlara uygun.'),
('QC2025017', 'APPROVED', 35, 'PRD005', 'Domates', 'BT202517', 2, 'Ayşe Kaya', '2025-02-03', '2025-02-01', 'Tazelik kontrolü başarılı.'),
('QC2025021', 'APPROVED', 45, 'PRD015', 'Sucuk', 'BT202521', 1, 'Ahmet Yılmaz', '2025-02-10', '2024-02-28', 'Tat, koku ve renk testleri başarılı.'),

-- Reddedilmiş kayıtlar
('QC2025003', 'REJECTED', 6, 'IND006', 'Kompresör', 'BT202503', 3, 'Mehmet Demir', '2025-01-10', '2024-03-06', 'Basınç testi başarısız. Valf contasında sızıntı tespit edildi.'),
('QC2025007', 'REJECTED', 15, 'IND015', 'Kaplin', 'BT202507', 4, 'Zeynep Çelik', '2025-01-17', '2024-02-28', 'Torsiyon testinde başarısız oldu. Malzeme yorgunluğu belirtisi gösteriyor.'),
('QC2025013', 'REJECTED', 23, 'IND023', 'Kontaktör', 'BT202513', 5, 'Mustafa Öztürk', '2025-01-27', '2024-02-20', 'Yük testlerinde başarısız, aşırı ısınma sorunu var.'),
('QC2025019', 'REJECTED', 36, 'PRD006', 'Yoğurt', 'BT202519', 4, 'Zeynep Çelik', '2025-02-05', '2025-02-04', 'pH değeri standart dışı, asitlik oranı yüksek.'),

-- Beklemede olan kayıtlar
('QC2025004', 'PENDING', 8, 'IND008', 'Konveyör Bant', 'BT202504', 2, 'Ayşe Kaya', '2025-01-12', '2024-02-20', 'Dayanıklılık testi sonuçları bekleniyor.'),
('QC2025006', 'PENDING', 12, 'IND012', 'Basınç Sensörü', 'BT202506', 1, 'Ahmet Yılmaz', '2025-01-15', '2024-03-07', 'Kalibrasyon sonuçları inceleniyor.'),
('QC2025011', 'PENDING', 18, 'IND018', 'Metal Toz Boya', 'BT202511', 3, 'Mehmet Demir', '2025-01-23', '2024-02-10', 'Adezyon testi bekleniyor.'),
('QC2025014', 'PENDING', 29, 'IND029', 'Kesici Takım Seti', 'BT202514', 2, 'Ayşe Kaya', '2025-01-28', '2023-11-15', 'Sertlik ve aşınma testleri sürüyor.'),
('QC2025018', 'PENDING', 37, 'PRD007', 'Makarna', 'BT202518', 3, 'Mehmet Demir', '2025-02-04', '2024-01-10', 'Pişirme testi sonuçları bekleniyor.'),

-- İşlemde olan kayıtlar
('QC2025005', 'IN_PROGRESS', 9, 'IND009', 'Redüktör', 'BT202505', 5, 'Mustafa Öztürk', '2025-01-14', '2024-03-09', 'Yük altında dayanıklılık testi devam ediyor.'),
('QC2025010', 'IN_PROGRESS', 17, 'IND017', 'PLC Kontrol Ünitesi', 'BT202510', 1, 'Ahmet Yılmaz', '2025-01-22', '2024-03-01', 'Yazılım fonksiyon testleri devam ediyor.'),
('QC2025015', 'IN_PROGRESS', 30, 'IND030', 'Lazer Sensör', 'BT202515', 3, 'Mehmet Demir', '2025-01-30', '2024-03-09', 'Hassasiyet ve tepki süresi testleri yapılıyor.'),
('QC2025020', 'IN_PROGRESS', 40, 'PRD010', 'Pirinç', 'BT202520', 5, 'Mustafa Öztürk', '2025-02-08', '2024-01-15', 'Pişme ve nem kontrolleri devam ediyor.');

-- Kalite Kontrol Kriterleri
INSERT INTO quality_criteria (name, expected_value, measured_value, passed, quality_control_id) VALUES
-- QC2025001 kriterleri
('Viskozite', '46 ±2 cSt (40°C)', '45.8 cSt', TRUE, 1),
('Aşınma Önleme', '> ASTM D2783', 'ASTM D2783+5%', TRUE, 1),
('Korozyon Direnci', 'ASTM D665A Geçti', 'ASTM D665A Geçti', TRUE, 1),
('Köpüklenme', 'ASTM D892 <50mL', '32mL', TRUE, 1),

-- QC2025002 kriterleri
('İç Çap', '25mm ±0.01mm', '25.008mm', TRUE, 2),
('Dış Çap', '52mm ±0.01mm', '52.005mm', TRUE, 2),
('Radyal Boşluk', '0.02mm-0.05mm', '0.035mm', TRUE, 2),

-- QC2025003 kriterleri
('Maksimum Basınç', '10 bar', '9.2 bar', FALSE, 3),
('Debi', '500L/dk ±5%', '482L/dk', TRUE, 3),
('Sızdırmazlık', 'Sızıntı Yok', 'Valf Contasında Sızıntı', FALSE, 3),

-- QC2025004 kriterleri
('Çekme Dayanımı', '> 25 MPa', 'Test Bekliyor', NULL, 4),
('Aşınma Direnci', '< 120 mm³', 'Test Bekliyor', NULL, 4),

-- QC2025005 kriterleri
('Dişli Boşluğu', '0.1mm-0.2mm', 'Test Sürüyor', NULL, 5),
('Çalışma Sıcaklığı', '<65°C', 'Test Sürüyor', NULL, 5),
('Tork İletimi', '>200 Nm', 'Test Sürüyor', NULL, 5),

-- QC2025016 kriterleri (Ekmek)
('Ağırlık', '400g ±10g', '395g', TRUE, 6),
('İç Yapı', 'Gözenekli ve Elastik', 'Gözenekli ve Elastik', TRUE, 6),
('Nem Oranı', '%35-40', '%38', TRUE, 6),
('pH Değeri', '5.3-5.7', '5.5', TRUE, 6),

-- QC2025019 kriterleri (Yoğurt)
('pH Değeri', '4.0-4.5', '3.8', FALSE, 10),
('Kıvam', 'Kremsi', 'Kremsi', TRUE, 10),
('Asitlik', '0.6-0.8%', '0.9%', FALSE, 10);

