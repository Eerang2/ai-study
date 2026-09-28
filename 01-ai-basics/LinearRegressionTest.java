public class LinearRegressionTest {
    public static void main(String[] args) {
        double[] hours = {1, 2, 3, 4, 5, 6, 7, 8};
        double[] scores = {38, 45, 55, 60, 72, 75, 88, 92};

        // y = a * x + b 에서 a는 기울기(slope), b는 절편(intercept)
        double slope = 0.0;      // a: 처음에는 임의의 값(0)으로 시작
        double intercept = 0.0;  // b: 처음에는 임의의 값(0)으로 시작
        double lr = 0.01;        // 학습률: 한 번에 움직이는 보폭
        int n = hours.length;

        for (int epoch = 0; epoch <= 5000; epoch++) {
            double loss = 0, gradSlope = 0, gradIntercept = 0;
            for (int i = 0; i < n; i++) {
                double prediction = slope * hours[i] + intercept;  // 예측
                double error = prediction - scores[i];             // 오차
                loss += error * error;                             // 오차 계산 (MSE)
                gradSlope += 2 * error * hours[i];
                gradIntercept += 2 * error;
            }
            loss /= n;
            gradSlope /= n;
            gradIntercept /= n;

            slope -= lr * gradSlope;          // 매개변수 조금씩 수정
            intercept -= lr * gradIntercept;

            if (epoch == 0 || epoch == 10 || epoch == 100 || epoch == 1000 || epoch == 5000) {
                System.out.printf("반복 %4d회 | 손실 %8.2f | 기울기 %.3f 절편 %.3f%n",
                        epoch, loss, slope, intercept);
            }
        }

        System.out.printf("최종 기울기(a): %.3f, 절편(b): %.3f%n", slope, intercept);
        System.out.printf("5.5시간 공부하면 예상 점수: %.1f점%n", slope * 5.5 + intercept);
    }
}