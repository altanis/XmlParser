#!/bin/bash

JAR_NAME='XMLParser-1.0-SNAPSHOT-jar-with-dependencies.jar'



ARGUMENTS="file:///home/slaskawiec/work/PRZEMEK/src/git/XMLParser/Package/xml/projekty_offline_mod_fixed.xml"\
" --output-directory ./output"\
" --output-csv-name index.csv"\
" --constant-img-main-uri-template ./images/wizualizacje/%s"\
" --constant-img-location-uri-template ./images/usytuowanie/%s"\
" --constant-img-projection-uri-template ./images/rzuty/%s"\
" --constant-img-elevation-uri-template ./images/elewacje/%s"\
" --output-img-width 640"\
" --output-img-height 480"\
" --xml-main-element /projekty/projekt"\
" --xml-project-name nazwa"\
" --xml-full-description opis"\
" --xml-short-description skrot"\
" --xml-category kategoria"\
" --xml-price-with-vat ceny/@brutto"\
" --xml-img-main zdjecia//zdjecie[@nazwa='zdjecie']/@plik"\
" --xml-img-projection kondygnacje//kondygnacja/@rzut"\
" --xml-img-elevation elewacje//elewacja/@zdjecie"\
" --xml-img-location zdjecia/zdjecie[@nazwa='teren']/@plik"\
" --xml-usable-space powierzchnie/@uzytkowa"\
" --xml-floors concat(kondygnacje//kondygnacja[1]/@nazwa,',',kondygnacje//kondygnacja[2]/@nazwa,',',kondygnacje//kondygnacja[3]/@nazwa)"
" --xml-garage ilosc_stanowisk_garazowych"\
" --xml-technology rodzaj"\
" --xml-roof-type rodzaj_dachu"\
" --xml-minimum-plot concat(wymiary/wymiar[@nazwa='min-szerokosc-dzialki'],' x ',wymiary/wymiar[@nazwa='min-dlugosc-dzialki'],' m')"\
" --xml-built-in-area concat(powierzchnie/powierzchnia[@nazwa='zabudowy'],' m2')"\
" --xml-volume concat(kubatura/@calkowita,' m3')"\
" --xml-building-height concat(wymiary/wymiar[@nazwa='wysokosc'],' m')"\
" --xml-slope-of-the-roof concat(wymiary/wymiar[@nazwa='kat-dachu'],'°')"\
" --xml-basement"\
" --constant-amount 99"\
" --constant-active 1"\
" --constant-img-link-template <img src=\"%s\" alt=\"\" />"

echo "PROGRAM ARGUMENTS: $ARGUMENTS"

java -jar $JAR_NAME $ARGUMENTS
    
