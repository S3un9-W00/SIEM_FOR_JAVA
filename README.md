# 프로젝트 구조
## Model

 - LogEntity.java : 로그 데이터를 위한 엔티티 클래스 (로그의 IP, 메시지 등 저장)

 - AlertEntity.java : 공격·이상 탐지 결과를 위한 엔티티 클래스 (의심 로그, 경고 관리)

## Repo

 - LogRepo.java : 로그 엔티티의 JPA 레포지토리 인터페이스 (로그 저장, 조회)

 - AlertRepo.java : 경고 엔티티용 JPA 레포지토리

## Service

 - SiemService.java : 핵심 SIEM 로직 서비스 클래스. 로그 저장·분석, 의심 키워드 탐지, 경고 및 로그 저장 절차 담당

 - 주요 기능: 로그 메시지에서 의심 단어 감지 ➔ 경고 기록, 로그인 실패 감지 시 경고 기록

## Controller

 - SiemController.java : 웹 요청 처리. 로그 등록(Post), 전체 로그 조회(Get), 경고 조회(Get), 폼 페이지 등 반환

 - 실제 웹 페이지는 HTML template(alerts.html, 등)로 출력
