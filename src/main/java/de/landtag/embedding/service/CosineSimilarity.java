package de.landtag.embedding.service;

public final class CosineSimilarity {

    private CosineSimilarity() {
    }

    public static double calculate(double[] leftVector, double[] rightVector) {
        double dotProduct = 0.0;
        double leftNorm = 0.0;
        double rightNorm = 0.0;

        for (int index = 0; index < leftVector.length; index++) {
            dotProduct += leftVector[index] * rightVector[index];
            leftNorm += leftVector[index] * leftVector[index];
            rightNorm += rightVector[index] * rightVector[index];
        }

        if (leftNorm == 0.0 || rightNorm == 0.0) {
            return 0.0;
        }

        return dotProduct / (Math.sqrt(leftNorm) * Math.sqrt(rightNorm));
    }

    public static double calculate(float[] leftVector, float[] rightVector) {
        double dotProduct = 0.0;
        double leftNorm = 0.0;
        double rightNorm = 0.0;

        for (int index = 0; index < leftVector.length; index++) {
            dotProduct += leftVector[index] * rightVector[index];
            leftNorm += leftVector[index] * leftVector[index];
            rightNorm += rightVector[index] * rightVector[index];
        }

        if (leftNorm == 0.0 || rightNorm == 0.0) {
            return 0.0;
        }

        return dotProduct / (Math.sqrt(leftNorm) * Math.sqrt(rightNorm));
    }
}
