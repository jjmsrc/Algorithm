-- APPOINTMENT의 PT_NO, MDDR_ID 활용하여 join
-- 진료예약번호, 환자이름, 환자번호, 진료과코드, 의사이름, 진료예약일시

SELECT a.APNT_NO, p.PT_NAME, p.PT_NO, d.MCDP_CD, d.DR_NAME, a.APNT_YMD
FROM 
    (
        SELECT *
        FROM APPOINTMENT a
        WHERE DATE(a.APNT_YMD)='2022-04-13' AND a.APNT_CNCL_YN='N'
    ) a 
    JOIN DOCTOR d ON a.MDDR_ID=d.DR_ID AND d.MCDP_CD='CS'
    JOIN PATIENT p ON a.PT_NO=p.PT_NO
ORDER BY a.APNT_YMD ASC
;


