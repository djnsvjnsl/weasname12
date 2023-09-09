/*
 * Copyright (C) 2023 Kevin Buzeau
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

#include <jni.h>
#include <opencv2/imgproc/imgproc.hpp>


std::unique_ptr<cv::Mat> createRGB565MatFromARGB8888Bitmap(JNIEnv *env, jobject bitmap);

std::unique_ptr<cv::Mat> createAndFillRGB565MatFromARGB8888Bitmap(JNIEnv *env, jobject bitmap);

void fillRGB565MatFromARGB8888Bitmap(JNIEnv *env, jobject bitmap, const cv::Mat& rgb565Mat);