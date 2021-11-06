var city = [
    {
        name: '서울특별시',
        code: 'CITY_01'
    },
    {
        name: '부산광역시',
        code: 'CITY_02'
    },
    {
        name: '대구광역시',
        code: 'CITY_03'
    },
    {
        name: '인천광역시',
        code: 'CITY_04'
    },
    {
        name: '광주광역시',
        code: 'CITY_05'
    },
    {
        name: '대전광역시',
        code: 'CITY_06'
    },{
        name: '울산광역시',
        code: 'CITY_07'
    },
    {
        name: '강원도',
        code: 'CITY_08'
    },
    {
        name: '경기도',
        code: 'CITY_09'
    },
    {
        name: '경상남도',
        code: 'CITY_10'
    },
    {
        name: '경상북도',
        code: 'CITY_11'
    },
    {
        name: '전라남도',
        code: 'CITY_12'
    },
    {
        name: '전라북도',
        code: 'CITY_13'
    },
    {
        name: '충청남도',
        code: 'CITY_14'
    },
    {
        name: '충청북도',
        code: 'CITY_15'
    },
    {
        name: '세종특별자치시',
        code: 'CITY_16'
    },
    {
        name: '제주특별자치도',
        code: 'CITY_17'
    }

];

var town = [
    // 서울 특별시
    {
        name: '강남구',
        code: 'TOWN_01',
        parent_cd: 'CITY_01'
    },
    {
        name: '강동구',
        code: 'TOWN_02',
        parent_cd: 'CITY_01'
    },
    {
        name: '강북구',
        code: 'TOWN_03',
        parent_cd: 'CITY_01'
    },
    {
        name: '강서구',
        code: 'TOWN_04',
        parent_cd: 'CITY_01'
    },
    {
        name: '관악구',
        code: 'TOWN_05',
        parent_cd: 'CITY_01'
    },
    {
        name: '광진구',
        code: 'TOWN_06',
        parent_cd: 'CITY_01'
    },
    {
        name: '구로구',
        code: 'TOWN_07',
        parent_cd: 'CITY_01'
    },
    {
        name: '금천구',
        code: 'TOWN_08',
        parent_cd: 'CITY_01'
    },
    {
        name: '노원구',
        code: 'TOWN_09',
        parent_cd: 'CITY_01'
    },
    {
        name: '도봉구',
        code: 'TOWN_10',
        parent_cd: 'CITY_01'
    },
    {
        name: '동대문구',
        code: 'TOWN_11',
        parent_cd: 'CITY_01'
    },
    {
        name: '동작구',
        code: 'TOWN_12',
        parent_cd: 'CITY_01'
    },
    {
        name: '마포구',
        code: 'TOWN_13',
        parent_cd: 'CITY_01'
    },
    {
        name: '서대문구',
        code: 'TOWN_14',
        parent_cd: 'CITY_01'
    },
    {
        name: '서초구',
        code: 'TOWN_15',
        parent_cd: 'CITY_01'
    },
    {
        name: '성동구',
        code: 'TOWN_16',
        parent_cd: 'CITY_01'
    },
    {
        name: '성북구',
        code: 'TOWN_17',
        parent_cd: 'CITY_01'
    },
    {
        name: '송파구',
        code: 'TOWN_18',
        parent_cd: 'CITY_01'
    },
    {
        name: '양천구',
        code: 'TOWN_19',
        parent_cd: 'CITY_01'
    },
    {
        name: '영등포구',
        code: 'TOWN_20',
        parent_cd: 'CITY_01'
    },
    {
        name: '용산구',
        code: 'TOWN_21',
        parent_cd: 'CITY_01'
    },
    {
        name: '은평구',
        code: 'TOWN_22',
        parent_cd: 'CITY_01'
    },
    {
        name: '종로구',
        code: 'TOWN_23',
        parent_cd: 'CITY_01'
    },
    {
        name: '중구',
        code: 'TOWN_24',
        parent_cd: 'CITY_01'
    },
    {
        name: '중랑구',
        code: 'TOWN_25',
        parent_cd: 'CITY_01'
    },

    // 부산 광역시시
    {
        name: '강서구',
        code: 'TOWN_26',
        parent_cd: 'CITY_02'
    },
    {
        name: '금정구',
        code: 'TOWN_27',
        parent_cd: 'CITY_02'
    },
    {
        name: '남구',
        code: 'TOWN_28',
        parent_cd: 'CITY_02'
    },
    {
        name: '동구',
        code: 'TOWN_29',
        parent_cd: 'CITY_02'
    },
    {
        name: '동래구',
        code: 'TOWN_30',
        parent_cd: 'CITY_02'
    },
    {
        name: '부산진구',
        code: 'TOWN_31',
        parent_cd: 'CITY_02'
    },
    {
        name: '북구',
        code: 'TOWN_32',
        parent_cd: 'CITY_02'
    },
    {
        name: '사상구',
        code: 'TOWN_33',
        parent_cd: 'CITY_02'
    },
    {
        name: '사하구',
        code: 'TOWN_34',
        parent_cd: 'CITY_02'
    },
    {
        name: '서구',
        code: 'TOWN_35',
        parent_cd: 'CITY_02'
    },
    {
        name: '수영구',
        code: 'TOWN_36',
        parent_cd: 'CITY_02'
    },
    {
        name: '연제구',
        code: 'TOWN_37',
        parent_cd: 'CITY_02'
    },
    {
        name: '영도구',
        code: 'TOWN_38',
        parent_cd: 'CITY_02'
    },
    {
        name: '중구',
        code: 'TOWN_39',
        parent_cd: 'CITY_02'
    },
    {
        name: '해운대구',
        code: 'TOWN_40',
        parent_cd: 'CITY_02'
    },
    {
        name: '기장군',
        code: 'TOWN_41',
        parent_cd: 'CITY_02'
    },

    // 대구 광역시
    {
        name: '남구',
        code: 'TOWN_42',
        parent_cd: 'CITY_03'
    },
    {
        name: '달서구',
        code: 'TOWN_43',
        parent_cd: 'CITY_03'
    },
    {
        name: '동구',
        code: 'TOWN_44',
        parent_cd: 'CITY_03'
    },
    {
        name: '북구',
        code: 'TOWN_45',
        parent_cd: 'CITY_03'
    },
    {
        name: '서구',
        code: 'TOWN_46',
        parent_cd: 'CITY_03'
    },
    {
        name: '수성구',
        code: 'TOWN_47',
        parent_cd: 'CITY_03'
    },
    {
        name: '중구',
        code: 'TOWN_48',
        parent_cd: 'CITY_03'
    },
    {
        name: '달성군',
        code: 'TOWN_49',
        parent_cd: 'CITY_03'
    },
    // 인천 광역시
    {
        name: '계양구',
        code: 'TOWN_50',
        parent_cd: 'CITY_04'
    },
    {
        name: '미추홀구',
        code: 'TOWN_51',
        parent_cd: 'CITY_04'
    },
    {
        name: '남동구',
        code: 'TOWN_52',
        parent_cd: 'CITY_04'
    },
    {
        name: '동구',
        code: 'TOWN_53',
        parent_cd: 'CITY_04'
    },
    {
        name: '부평구',
        code: 'TOWN_54',
        parent_cd: 'CITY_04'
    },
    {
        name: '서구',
        code: 'TOWN_55',
        parent_cd: 'CITY_04'
    },
    {
        name: '연수구',
        code: 'TOWN_56',
        parent_cd: 'CITY_04'
    },
    {
        name: '중구',
        code: 'TOWN_57',
        parent_cd: 'CITY_04'
    },
    {
        name: '강화군',
        code: 'TOWN_58',
        parent_cd: 'CITY_04'
    },
    {
        name: '옹진군',
        code: 'TOWN_59',
        parent_cd: 'CITY_04'
    },
    // 광주광역시
    {
        name: '광산구',
        code: 'TOWN_60',
        parent_cd: 'CITY_05'
    },
    {
        name: '남구',
        code: 'TOWN_61',
        parent_cd: 'CITY_05'
    },
    {
        name: '동구',
        code: 'TOWN_62',
        parent_cd: 'CITY_05'
    },
    {
        name: '북구',
        code: 'TOWN_63',
        parent_cd: 'CITY_05'
    },
    {
        name: '서구',
        code: 'TOWN_64',
        parent_cd: 'CITY_05'
    },
    // 대전광역시
    {
        name: '대덕구',
        code: 'TOWN_65',
        parent_cd: 'CITY_06'
    },
    {
        name: '동구',
        code: 'TOWN_66',
        parent_cd: 'CITY_06'
    },
    {
        name: '서구',
        code: 'TOWN_67',
        parent_cd: 'CITY_06'
    },
    {
        name: '유성구',
        code: 'TOWN_68',
        parent_cd: 'CITY_06'
    },
    {
        name: '중구',
        code: 'TOWN_69',
        parent_cd: 'CITY_06'
    },
    // 울산광역시
    {
        name: '남구',
        code: 'TOWN_70',
        parent_cd: 'CITY_07'
    },
    {
        name: '동구',
        code: 'TOWN_71',
        parent_cd: 'CITY_07'
    },
    {
        name: '북구',
        code: 'TOWN_72',
        parent_cd: 'CITY_07'
    },
    {
        name: '중구',
        code: 'TOWN_73',
        parent_cd: 'CITY_07'
    },
    {
        name: '울주군',
        code: 'TOWN_74',
        parent_cd: 'CITY_07'
    },
    // 강원도
    {
        name: '강릉시',
        code: 'TOWN_75',
        parent_cd: 'CITY_08'
    },
    {
        name: '고성군',
        code: 'TOWN_76',
        parent_cd: 'CITY_08'
    },
    {
        name: '동해시',
        code: 'TOWN_77',
        parent_cd: 'CITY_08'
    },
    {
        name: '삼척시',
        code: 'TOWN_78',
        parent_cd: 'CITY_08'
    },
    {
        name: '속초시',
        code: 'TOWN_79',
        parent_cd: 'CITY_08'
    },
    {
        name: '양구군',
        code: 'TOWN_80',
        parent_cd: 'CITY_08'
    },
    {
        name: '양양군',
        code: 'TOWN_81',
        parent_cd: 'CITY_08'
    },
    {
        name: '영월군',
        code: 'TOWN_82',
        parent_cd: 'CITY_08'
    },
    {
        name: '원주시',
        code: 'TOWN_83',
        parent_cd: 'CITY_08'
    },
    {
        name: '인제군',
        code: 'TOWN_84',
        parent_cd: 'CITY_08'
    },
    {
        name: '정선군',
        code: 'TOWN_85',
        parent_cd: 'CITY_08'
    },
    {
        name: '철원군',
        code: 'TOWN_86',
        parent_cd: 'CITY_08'
    },
    {
        name: '춘천시',
        code: 'TOWN_87',
        parent_cd: 'CITY_08'
    },
    {
        name: '태백시',
        code: 'TOWN_88',
        parent_cd: 'CITY_08'
    },
    {
        name: '평창군',
        code: 'TOWN_89',
        parent_cd: 'CITY_08'
    },
    {
        name: '홍천군',
        code: 'TOWN_90',
        parent_cd: 'CITY_08'
    },
    {
        name: '화천군',
        code: 'TOWN_91',
        parent_cd: 'CITY_08'
    },
    {
        name: '황성군',
        code: 'TOWN_92',
        parent_cd: 'CITY_08'
    },
    // 경기도
    {
        name: '가평군',
        code: 'TOWN_93',
        parent_cd: 'CITY_09'
    },
    {
        name: '고양시',
        code: 'TOWN_94',
        parent_cd: 'CITY_09'
    },
    {
        name: '과천시',
        code: 'TOWN_95',
        parent_cd: 'CITY_09'
    },
    {
        name: '광명시',
        code: 'TOWN_96',
        parent_cd: 'CITY_09'
    },
    {
        name: '광주시',
        code: 'TOWN_97',
        parent_cd: 'CITY_09'
    },
    {
        name: '구리시',
        code: 'TOWN_98',
        parent_cd: 'CITY_09'
    },
    {
        name: '군포시',
        code: 'TOWN_99',
        parent_cd: 'CITY_09'
    },
    {
        name: '김포시',
        code: 'TOWN_100',
        parent_cd: 'CITY_09'
    },
    {
        name: '남양주시',
        code: 'TOWN_101',
        parent_cd: 'CITY_09'
    },
    {
        name: '동두천시',
        code: 'TOWN_102',
        parent_cd: 'CITY_09'
    },
    {
        name: '부천시',
        code: 'TOWN_103',
        parent_cd: 'CITY_09'
    },
    {
        name: '성남시',
        code: 'TOWN_104',
        parent_cd: 'CITY_09'
    },
    {
        name: '수원시',
        code: 'TOWN_105',
        parent_cd: 'CITY_09'
    },
    {
        name: '시흥시',
        code: 'TOWN_106',
        parent_cd: 'CITY_09'
    },
    {
        name: '안산시',
        code: 'TOWN_107',
        parent_cd: 'CITY_09'
    },
    {
        name: '안양시',
        code: 'TOWN_108',
        parent_cd: 'CITY_09'
    },
    {
        name: '양주시',
        code: 'TOWN_109',
        parent_cd: 'CITY_09'
    },
    {
        name: '양평군',
        code: 'TOWN_110',
        parent_cd: 'CITY_09'
    },
    {
        name: '여주시',
        code: 'TOWN_111',
        parent_cd: 'CITY_09'
    },
    {
        name: '연천군',
        code: 'TOWN_112',
        parent_cd: 'CITY_09'
    },
    {
        name: '오산시',
        code: 'TOWN_113',
        parent_cd: 'CITY_09'
    },
    {
        name: '용인시',
        code: 'TOWN_114',
        parent_cd: 'CITY_09'
    },
    {
        name: '의왕시',
        code: 'TOWN_115',
        parent_cd: 'CITY_09'
    },
    {
        name: '의정부시',
        code: 'TOWN_116',
        parent_cd: 'CITY_09'
    },
    {
        name: '이천시',
        code: 'TOWN_117',
        parent_cd: 'CITY_09'
    },
    {
        name: '파주시',
        code: 'TOWN_118',
        parent_cd: 'CITY_09'
    },
    {
        name: '평택시',
        code: 'TOWN_119',
        parent_cd: 'CITY_09'
    },
    {
        name: '포천시',
        code: 'TOWN_120',
        parent_cd: 'CITY_09'
    },
    {
        name: '하남시',
        code: 'TOWN_121',
        parent_cd: 'CITY_09'
    },
    {
        name: '화성시',
        code: 'TOWN_122',
        parent_cd: 'CITY_09'
    },
    {
        name: '안성시',
        code: 'TOWN_123',
        parent_cd: 'CITY_09'
    },
    // 경상남도
    {
        name: '거제시',
        code: 'TOWN_124',
        parent_cd: 'CITY_10'
    },
    {
        name: '거창군',
        code: 'TOWN_125',
        parent_cd: 'CITY_10'
    },
    {
        name: '고성군',
        code: 'TOWN_126',
        parent_cd: 'CITY_10'
    },
    {
        name: '김해시',
        code: 'TOWN_127',
        parent_cd: 'CITY_10'
    },
    {
        name: '남해군',
        code: 'TOWN_128',
        parent_cd: 'CITY_10'
    },
    {
        name: '마산시',
        code: 'TOWN_129',
        parent_cd: 'CITY_10'
    },
    {
        name: '밀양시',
        code: 'TOWN_130',
        parent_cd: 'CITY_10'
    },
    {
        name: '사천시',
        code: 'TOWN_131',
        parent_cd: 'CITY_10'
    },
    {
        name: '산청군',
        code: 'TOWN_132',
        parent_cd: 'CITY_10'
    },
    {
        name: '양산시',
        code: 'TOWN_133',
        parent_cd: 'CITY_10'
    },
    {
        name: '의령군',
        code: 'TOWN_134',
        parent_cd: 'CITY_10'
    },
    {
        name: '진주시',
        code: 'TOWN_135',
        parent_cd: 'CITY_10'
    },
    {
        name: '창녕군',
        code: 'TOWN_136',
        parent_cd: 'CITY_10'
    },
    {
        name: '창원시',
        code: 'TOWN_137',
        parent_cd: 'CITY_10'
    },
    {
        name: '통영시',
        code: 'TOWN_138',
        parent_cd: 'CITY_10'
    },
    {
        name: '하동군',
        code: 'TOWN_139',
        parent_cd: 'CITY_10'
    },
    {
        name: '함안군',
        code: 'TOWN_140',
        parent_cd: 'CITY_10'
    },
    {
        name: '함양군',
        code: 'TOWN_141',
        parent_cd: 'CITY_10'
    },
    {
        name: '합천군',
        code: 'TOWN_142',
        parent_cd: 'CITY_10'
    },
    // 경상북도
    {
        name: '경산시',
        code: 'TOWN_143',
        parent_cd: 'CITY_11'
    },
    {
        name: '경주시',
        code: 'TOWN_144',
        parent_cd: 'CITY_11'
    },
    {
        name: '구미시',
        code: 'TOWN_145',
        parent_cd: 'CITY_11'
    },
    {
        name: '김천시',
        code: 'TOWN_146',
        parent_cd: 'CITY_11'
    },
    {
        name: '문경시',
        code: 'TOWN_147',
        parent_cd: 'CITY_11'
    },
    {
        name: '상주시',
        code: 'TOWN_148',
        parent_cd: 'CITY_11'
    },
    {
        name: '안동시',
        code: 'TOWN_149',
        parent_cd: 'CITY_11'
    },
    {
        name: '영주시',
        code: 'TOWN_150',
        parent_cd: 'CITY_11'
    },
    {
        name: '영천시',
        code: 'TOWN_151',
        parent_cd: 'CITY_11'
    },
    {
        name: '포항시',
        code: 'TOWN_152',
        parent_cd: 'CITY_11'
    },
    {
        name: '고령군',
        code: 'TOWN_153',
        parent_cd: 'CITY_11'
    },
    {
        name: '군위군',
        code: 'TOWN_154',
        parent_cd: 'CITY_11'
    },
    {
        name: '봉화군',
        code: 'TOWN_155',
        parent_cd: 'CITY_11'
    },
    {
        name: '상주군',
        code: 'TOWN_156',
        parent_cd: 'CITY_11'
    },
    {
        name: '영덕군',
        code: 'TOWN_157',
        parent_cd: 'CITY_11'
    },
    {
        name: '영양군',
        code: 'TOWN_158',
        parent_cd: 'CITY_11'
    },
    {
        name: '예천군',
        code: 'TOWN_159',
        parent_cd: 'CITY_11'
    },
    {
        name: '울릉군',
        code: 'TOWN_160',
        parent_cd: 'CITY_11'
    },
    {
        name: '울진군',
        code: 'TOWN_161',
        parent_cd: 'CITY_11'
    },
    {
        name: '의성군',
        code: 'TOWN_162',
        parent_cd: 'CITY_11'
    },
    {
        name: '청도군',
        code: 'TOWN_163',
        parent_cd: 'CITY_11'
    },
    {
        name: '청송군',
        code: 'TOWN_164',
        parent_cd: 'CITY_11'
    },
    {
        name: '칠곡군',
        code: 'TOWN_165',
        parent_cd: 'CITY_11'
    },
    // 전라남도
    {
        name: '광양시',
        code: 'TOWN_166',
        parent_cd: 'CITY_12'
    },
    {
        name: '나주시',
        code: 'TOWN_167',
        parent_cd: 'CITY_12'
    },
    {
        name: '목포시',
        code: 'TOWN_168',
        parent_cd: 'CITY_12'
    },
    {
        name: '순천시',
        code: 'TOWN_169',
        parent_cd: 'CITY_12'
    },
    {
        name: '여수시',
        code: 'TOWN_170',
        parent_cd: 'CITY_12'
    },
    {
        name: '강진군',
        code: 'TOWN_171',
        parent_cd: 'CITY_12'
    },
    {
        name: '고흥군',
        code: 'TOWN_172',
        parent_cd: 'CITY_12'
    },
    {
        name: '곡성군',
        code: 'TOWN_173',
        parent_cd: 'CITY_12'
    },
    {
        name: '구례군',
        code: 'TOWN_174',
        parent_cd: 'CITY_12'
    },
    {
        name: '담양군',
        code: 'TOWN_175',
        parent_cd: 'CITY_12'
    },
    {
        name: '무안군',
        code: 'TOWN_176',
        parent_cd: 'CITY_12'
    },
    {
        name: '보성군',
        code: 'TOWN_177',
        parent_cd: 'CITY_12'
    },
    {
        name: '신안군',
        code: 'TOWN_178',
        parent_cd: 'CITY_12'
    },
    {
        name: '영광군',
        code: 'TOWN_179',
        parent_cd: 'CITY_12'
    },
    {
        name: '영암군',
        code: 'TOWN_180',
        parent_cd: 'CITY_12'
    },
    {
        name: '완도군',
        code: 'TOWN_181',
        parent_cd: 'CITY_12'
    },
    {
        name: '장성군',
        code: 'TOWN_182',
        parent_cd: 'CITY_12'
    },
    {
        name: '장흥군',
        code: 'TOWN_183',
        parent_cd: 'CITY_12'
    },
    {
        name: '진도군',
        code: 'TOWN_184',
        parent_cd: 'CITY_12'
    },
    {
        name: '함평군',
        code: 'TOWN_185',
        parent_cd: 'CITY_12'
    },
    {
        name: '해남군',
        code: 'TOWN_186',
        parent_cd: 'CITY_12'
    },
    {
        name: '화순군',
        code: 'TOWN_187',
        parent_cd: 'CITY_12'
    },
    // 전라북도
    {
        name: '군산시',
        code: 'TOWN_188',
        parent_cd: 'CITY_13'
    },
    {
        name: '김제시',
        code: 'TOWN_189',
        parent_cd: 'CITY_13'
    },
    {
        name: '남원시',
        code: 'TOWN_190',
        parent_cd: 'CITY_13'
    },
    {
        name: '익산시',
        code: 'TOWN_191',
        parent_cd: 'CITY_13'
    },
    {
        name: '전주시',
        code: 'TOWN_192',
        parent_cd: 'CITY_13'
    },
    {
        name: '정읍시',
        code: 'TOWN_193',
        parent_cd: 'CITY_13'
    },
    {
        name: '고창군',
        code: 'TOWN_194',
        parent_cd: 'CITY_13'
    },
    {
        name: '무주군',
        code: 'TOWN_195',
        parent_cd: 'CITY_13'
    },
    {
        name: '부안군',
        code: 'TOWN_196',
        parent_cd: 'CITY_13'
    },
    {
        name: '순창군',
        code: 'TOWN_197',
        parent_cd: 'CITY_13'
    },
    {
        name: '완주군',
        code: 'TOWN_198',
        parent_cd: 'CITY_13'
    },
    {
        name: '임실군',
        code: 'TOWN_199',
        parent_cd: 'CITY_13'
    },
    {
        name: '장수군',
        code: 'TOWN_200',
        parent_cd: 'CITY_13'
    },
    {
        name: '진안군',
        code: 'TOWN_201',
        parent_cd: 'CITY_13'
    },
    // 충청남도
    {
        name: '계롱시',
        code: 'TOWN_202',
        parent_cd: 'CITY_14'
    },
    {
        name: '공주시',
        code: 'TOWN_203',
        parent_cd: 'CITY_14'
    },
    {
        name: '논산시',
        code: 'TOWN_204',
        parent_cd: 'CITY_14'
    },
    {
        name: '보령시',
        code: 'TOWN_205',
        parent_cd: 'CITY_14'
    },
    {
        name: '서산시',
        code: 'TOWN_206',
        parent_cd: 'CITY_14'
    },
    {
        name: '아산시',
        code: 'TOWN_207',
        parent_cd: 'CITY_14'
    },
    {
        name: '천안시',
        code: 'TOWN_208',
        parent_cd: 'CITY_14'
    },
    {
        name: '금산군',
        code: 'TOWN_209',
        parent_cd: 'CITY_14'
    },
    {
        name: '당진시',
        code: 'TOWN_210',
        parent_cd: 'CITY_14'
    },
    {
        name: '부여군',
        code: 'TOWN_211',
        parent_cd: 'CITY_14'
    },
    {
        name: '서천군',
        code: 'TOWN_212',
        parent_cd: 'CITY_14'
    },
    {
        name: '연기군',
        code: 'TOWN_213',
        parent_cd: 'CITY_14'
    },
    {
        name: '예산군',
        code: 'TOWN_214',
        parent_cd: 'CITY_14'
    },
    {
        name: '청양군',
        code: 'TOWN_215',
        parent_cd: 'CITY_14'
    },
    {
        name: '태안군',
        code: 'TOWN_216',
        parent_cd: 'CITY_14'
    },
    {
        name: '홍성군',
        code: 'TOWN_217',
        parent_cd: 'CITY_14'
    },
    // 충청북도
    {
        name: '제천시',
        code: 'TOWN_218',
        parent_cd: 'CITY_15'
    },
    {
        name: '청주시',
        code: 'TOWN_219',
        parent_cd: 'CITY_15'
    },
    {
        name: '충주시',
        code: 'TOWN_220',
        parent_cd: 'CITY_15'
    },
    {
        name: '괴산군',
        code: 'TOWN_221',
        parent_cd: 'CITY_15'
    },
    {
        name: '단양군',
        code: 'TOWN_222',
        parent_cd: 'CITY_15'
    },
    {
        name: '보은군',
        code: 'TOWN_223',
        parent_cd: 'CITY_15'
    },
    {
        name: '영동군',
        code: 'TOWN_224',
        parent_cd: 'CITY_15'
    },
    {
        name: '옥천군',
        code: 'TOWN_225',
        parent_cd: 'CITY_15'
    },
    {
        name: '음성군',
        code: 'TOWN_226',
        parent_cd: 'CITY_15'
    },
    {
        name: '증평군',
        code: 'TOWN_227',
        parent_cd: 'CITY_15'
    },
    {
        name: '진천군',
        code: 'TOWN_228',
        parent_cd: 'CITY_15'
    },
    {
        name: '청원군',
        code: 'TOWN_229',
        parent_cd: 'CITY_15'
    },
    // 세종 특별시 ( 없음 )

    // 제주 특별시
    {
        name: '제주시',
        code: 'TOWN_230',
        parent_cd: 'CITY_17'
    },
    {
        name: '서귀포시',
        code: 'TOWN_231',
        parent_cd: 'CITY_17'
    }
];


