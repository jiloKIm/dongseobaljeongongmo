package com.example.demo.Service;

import com.example.demo.Entity.Building;
import com.example.demo.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class BuildService {

    private final BuildRepository buildingRepository; // BuildingRepository를 주입받음
    private final UserRepository userRepository;



    public Building save(String name, String electricKwh, String heatingCoolingArea, String gasKwh, String floorArea) {
        // Building 엔티티를 생성하고 User 객체를 설정

        Building building = new Building(name, electricKwh, heatingCoolingArea, gasKwh, floorArea);
        buildingRepository.save(building);

        return building; // Building 객체를 저장하고 반환
    }

    public ClassificationResult view(Long id){
        // Building 객체를 찾고 없는 경우 예외 처리
        Building building = buildingRepository.findById(id).orElseThrow(() -> new RuntimeException("Building not found"));

        // 기존 로직
        ClassificationResult result = classifyBuilding(building.getElectricKwh(), building.getHeatingCoolingArea(), building.getGasKwh(),building.getFloorArea());
        return result;
    }

    public static class ClassificationResult {
        private String className;
        private String solution;

        private Double sum;

        public ClassificationResult(String className, String solution, Double sum) {
            this.className = className;
            this.solution = solution;
            this.sum= sum;
        }

        public Double getSum() {
            return sum;
        }

        public String getClassName() {
            return className;
        }

        public String getSolution() {
            return solution;
        }
    }

    public static ClassificationResult classifyBuilding(String electricKwh, String heatingCoolingArea, String gasKwh,String floorArea) {
        double electricKwhValue = Double.parseDouble(electricKwh);
        double heatingCoolingAreaValue = Double.parseDouble(heatingCoolingArea);
        double gasKwhValue = Double.parseDouble(gasKwh);
        double floorarea=  Double.parseDouble(floorArea);
                String className;
        String solution;
        double sum=30.41+0.0001597* electricKwhValue-0.00579*heatingCoolingAreaValue+0.0000152*gasKwhValue +0.000595*floorarea;


        if (electricKwhValue <= 98422.60 && heatingCoolingAreaValue <= 3202.00 && electricKwhValue <= 60151.50) {
            className = "Low";
            solution = "Solution for Low class:\n" +
                    "1. LED 조명 시스템 도입\n" +
                    "2. 스마트 전기 관리 시스템 도입\n" +
                    "3. 단열재 적용";
        } else if (electricKwhValue <= 98422.60 && heatingCoolingAreaValue <= 3202.00 && electricKwhValue > 60151.50) {
            className = "Medium";
            solution = "Solution for Medium class:\n" +
                    "1. 스마트 냉난방 시스템 도입\n" +
                    "2. 창문 및 단열 개선\n" +
                    "3. 전기 사용량 모니터링";
        } else if (electricKwhValue <= 98422.60 && heatingCoolingAreaValue > 3202.00 && gasKwhValue <= 459475.59) {
            className = "Low";
            solution = "Solution for Low class:\n" +
                    "1. 가스 냉난방 시스템 도입\n" +
                    "2. 에너지 효율적인 가스 보일러 사용";
        } else if (electricKwhValue <= 98422.60 && heatingCoolingAreaValue > 3202.00 && gasKwhValue > 459475.59) {
            className = "Medium";
            solution = "Solution for Medium class:\n" +
                    "1. 가스 냉난방 및 난방 시스템 업그레이드\n" +
                    "2. 에너지 스타 등급 가전제품 사용";
        } else if (electricKwhValue > 98422.60 && electricKwhValue <= 191028.00 && heatingCoolingAreaValue <= 5192.74) {
            className = "Medium";
            solution = "Solution for Medium class:\n" +
                    "1. 에너지 효율적인 가전제품 사용\n" +
                    "2. 냉난방면적 관리";
        } else if (electricKwhValue > 98422.60 && electricKwhValue <= 191028.00 && heatingCoolingAreaValue > 5192.74) {
            className = "Low";
            solution = "Solution for Low class:\n" +
                    "1. 에너지 모니터링 시스템 구축\n" +
                    "2. 에너지 절약 교육";
        } else if (electricKwhValue > 98422.60 && electricKwhValue > 191028.00 && heatingCoolingAreaValue <= 6739.42) {
            className = "High";
            solution = "Solution for High class:\n" +
                    "1. 태양광 발전소 설치\n" +
                    "2. 냉난방 면적 최적화";
        } else {
            className = "Medium";
            solution = "Default solution for Medium class:\n" +
                    "1. 에너지 효율적인 가전제품 도입\n" +
                    "2. 태양열 및 지열 시스템 도입\n" +
                    "3. 건물 열원가 개선";
        }

        return new ClassificationResult(className, solution,sum);
    }


}
