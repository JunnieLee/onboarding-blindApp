package com.example.blindapp.data.source.local

import androidx.room.TypeConverter
import com.example.blindapp.util.DateUtil
import java.util.Date

class DateConverter {

    @TypeConverter
    fun toDate(timestamp:String?): Date?{
        return timestamp?.let{ DateUtil.dbDateFormat.parse(it) }
    } // 이 부분을 통해 raw값을 entity의 Date 형식으로 변환해 리턴해주는것임.

    @TypeConverter
    fun toTimeStamp(date:Date?):String?{
        return DateUtil.dbDateFormat.format(date)
    } // 이 부분을 통해 DB에 적재됨

}