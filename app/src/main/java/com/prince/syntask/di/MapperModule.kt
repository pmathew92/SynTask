package com.prince.syntask.di

import com.prince.data.mapper.ExclusionMapper
import com.prince.data.mapper.VariantMapper
import com.prince.data.mapper.VariationGroupMapper
import com.prince.data.mapper.VariationMapper
import com.prince.data.model.ExclusionsNetwork
import com.prince.data.model.VariantGroupNetwork
import com.prince.data.model.VariantsNetwork
import com.prince.data.model.VariationNetwork
import com.prince.domain.entities.ExclusionsEntity
import com.prince.domain.entities.VariantGroupEntity
import com.prince.domain.entities.VariantsEntity
import com.prince.domain.entities.VariationEntity
import com.prince.syntask.mapper.*
import com.prince.syntask.model.Exclusions
import com.prince.syntask.model.VariantGroup
import com.prince.syntask.model.Variants
import com.prince.syntask.model.Variation
import dagger.Binds
import dagger.Module

@Module
abstract class MapperModule {


    //app module mappers
    @Binds
    abstract fun bindsVariantEntityMapper(
        variantMapper: VariantEntityMapper
    ): Mapper<VariantsEntity, Variants>


    @Binds
    abstract fun bindsExclusionEntityMapper(
        exclusionMapper: ExclusionEntityMapper
    ): Mapper<ExclusionsEntity, Exclusions>


    @Binds
    abstract fun bindsVariantGroupEntityMapper(
        groupMapper: VariantGroupEntityMapper
    ): Mapper<VariantGroupEntity, VariantGroup>


    @Binds
    abstract fun bindsVariationEntityMapper(
        variationMapper: VariationEntityMapper
    ): Mapper<VariationEntity, Variation>


    //data module mappers
    @Binds
    abstract fun bindsVariantMapper(
        variantMapper: VariantMapper
    ): com.prince.data.mapper.Mapper<VariantsNetwork, VariantsEntity>


    @Binds
    abstract fun bindsExclusionMapper(
        exclusionMapper: ExclusionMapper
    ): com.prince.data.mapper.Mapper<ExclusionsNetwork, ExclusionsEntity>


    @Binds
    abstract fun bindsVariantGroupMapper(
        groupMapper: VariationGroupMapper
    ): com.prince.data.mapper.Mapper<VariantGroupNetwork, VariantGroupEntity>


    @Binds
    abstract fun bindsVariationMapper(
        variationMapper: VariationMapper
    ): com.prince.data.mapper.Mapper<VariationNetwork, VariationEntity>
}