package com.ict.projecttodo.models

import android.icu.text.CaseMap.Title

data class Post (val userId:Int,val id:Int , val title: String? ,val completed:Boolean)