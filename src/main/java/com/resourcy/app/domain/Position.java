package com.resourcy.app.domain;

public enum Position {
    ANALÜÜTIK("Analüütik-konsultant"),
    DIVISJONIJUHT("Divisjonijuht"),
    EELTÖÖTLUS("Eeltöötluse operaator"),
    JUHTIV_ANALÜÜTIK("Juhtiv analüütik-konsultant"),
    JUHTIVPROJEKTEERIJA("Juhtivprojekteerija"),
    JUHTIVTESTIJA("Juhtivtestija"),
    KASUTAJATUGI("Kasutajatoe spetsialist"),
    KAUGSEIRE("Kaugseire konsultant"),
    KLIENDITUGI("Klienditoe vanemsüsteemiadministraator"),
    MEESKONNAJUHT("Meeskonnajuht"),
    NOOREMPROGRAMMEERIJA("Nooremprogrammeerija"),
    PROGRAMMEERIJA_PRAKTIKANT("Nooremprogrammeerija/praktikant"),
    NOOREMSYSANALÜÜTIK("Nooremsüsteemianalüütik"),
    TESTIJA_PRAKTIKANT("Nooremtestija/praktikant"),
    ORACLEDBA("OracleDBA"),
    PROGRAMMEERIJA("Programmeerija"),
    PROJEKTEERIJA("Projekteerija"),
    PROJEKTIJUHT("Projektijuht"),
    RAKENDUSADMIN("Rakendusadministraator"),
    RELIISIJUHT("Reliisijuht"),
    SÜSANALÜÜTIK("Süsteemianalüütik"),
    TARKVARAARENDAJA("Tarkvara arendaja"),
    TARKVARAARHITEKT("Tarkvaraarhitekt"),
    TESTIJA("Testija"),
    TESTIMISEJUHT("Testimise juht"),
    VANEMPROGRAMMEERIJA("Vanemprogrameerija"),
    ÄRIKONSULTANT("Ärirakendustarkvara konsultant");

    public final String val;

    private Position(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}
