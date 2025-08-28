WITH truck AS (
    SELECT CAR_ID, DAILY_FEE
    FROM CAR_RENTAL_COMPANY_CAR 
    WHERE CAR_TYPE='트럭'
),
history AS (
    SELECT t.CAR_ID, t.DAILY_FEE, h.HISTORY_ID, DATEDIFF(h.END_DATE, h.START_DATE) + 1 diff
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY h, truck t
    WHERE h.CAR_ID=t.CAR_ID
),
history_dc AS (
    SELECT h.*, 
        CASE 
            WHEN h.diff < 7 THEN null
            WHEN h.diff >= 7 AND h.diff < 30 THEN '7일 이상'
            WHEN h.diff >= 30 AND h.diff < 90 THEN '30일 이상'
            ELSE '90일 이상'
        END DURATION_TYPE
    FROM history h
),
dc_truck AS (
    SELECT *
    FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
    WHERE CAR_TYPE='트럭'
)
SELECT 
    h.HISTORY_ID, 
    ROUND((h.DAILY_FEE * h.diff) * (1.0 - IFNULL(dc.DISCOUNT_RATE, 0) / 100)) AS FEE 
FROM history_dc h LEFT JOIN dc_truck dc ON h.DURATION_TYPE=dc.DURATION_TYPE
ORDER BY FEE DESC, h.HISTORY_ID DESC

